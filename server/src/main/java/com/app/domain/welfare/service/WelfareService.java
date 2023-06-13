package com.app.domain.welfare.service;

import com.app.domain.member.entity.Member;
import com.app.domain.program.client.ProgramRetrieveClient;
import com.app.domain.program.dto.ProgramDto;
import com.app.domain.program.dto.ProgramDto.ListResponse;
import com.app.domain.program.repository.ProgramRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class WelfareService {


    private final ObjectMapper objectMapper;

    private final WordAnalysisService wordAnalysisService;

    private final ProgramRetrieveClient programRetrieveClient;

    private final ProgramRepository programRepository;

    @Value("${welfare.serviceKey}")
    private String serviceKey;

    @Value("${welfare.clientId}")
    private String clientId;

    @Value("${welfare.clientSecret}")
    private String clientSecret;

    private final String pageNo = "1";
    private final String numOfRows = "5";

    private final String lifeArray = "005,006";

    private final String srchKeyCode = "003";

    private final String arrgOrd = "001";

    public ListResponse searchWelfareByVoice(MultipartFile multipartFile, Member member)
        throws Exception {

        File audioFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(audioFile);
        fos.write(multipartFile.getBytes());
        fos.close();

        String token = getToken();
        String transcribeId = postTranscribe(token, audioFile);

        try {
            //Initial Wait(처음 5초간 대기)
            Thread.sleep(5000);
            String status = "";
            while (!"completed".equals(status)) {
                String response = getTranscribe(token, transcribeId);
                JsonNode jsonNode = objectMapper.readTree(response);
                status = jsonNode.get("status").asText();
                // If status is not "completed", wait for another 5 seconds
                if (!"completed".equals(status)) {
                    Thread.sleep(1000);
                }

            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        String response = getTranscribe(token, transcribeId);
        JsonNode jsonNode = objectMapper.readTree(response);
        JsonNode utterancesNode = jsonNode.get("results").get("utterances");
        StringBuilder result = new StringBuilder();
        for (JsonNode utterance : utterancesNode) {
            String msg = utterance.get("msg").asText();
            result.append(msg).append(" ");
        }

        if (audioFile.exists()) {
            audioFile.delete();
        }

        Map<String, Integer> rMap = wordAnalysisService.doWordAnalysis(result.toString());

        if (rMap == null || rMap.size() == 0) {
            rMap = new HashMap<>();
            rMap.put("국가", 1);
        }

        String searchWrd = "";
        int count = 0;
        for (String s : rMap.keySet()) {
            if (rMap.get(s) > count) {
                count = rMap.get(s);
                searchWrd = s;
            } else if (rMap.get(s) == count) {
                searchWrd = searchWrd.compareTo(s) <= 0 ? searchWrd : s;
            }
        }

        log.info("searchWrd={}", searchWrd);
        if (member == null) {
            return callWelfareApi(searchWrd);
        } else {
            return callWelfareApi(searchWrd, member);
        }

    }

    private ListResponse callWelfareApi(String searchWrd, Member member) {

        ProgramDto.ListRequest programListRequestDto = ProgramDto.ListRequest.builder()
            .serviceKey(serviceKey)
            .pageNo(pageNo)
            .numOfRows(numOfRows)
            .lifeArray(lifeArray)
            .srchKeyCode(srchKeyCode)
            .searchWrd(searchWrd)
            .arrgOrd(arrgOrd)
            .build();

        ProgramDto.ListResponse programListResponse = programRetrieveClient.getList(
            programListRequestDto);

        List<String> serviceIdList = programRepository.findByMember(member.getMemberId());

        for (ProgramDto.ServList p : programListResponse.getServList()) {
            p.checkLike(serviceIdList.contains(p.getServId()));
        }
        return programListResponse;
    }

    private ListResponse callWelfareApi(String searchWrd) {

        ProgramDto.ListRequest programListRequestDto = ProgramDto.ListRequest.builder()
            .serviceKey(serviceKey)
            .pageNo(pageNo)
            .numOfRows(numOfRows)
            .lifeArray(lifeArray)
            .srchKeyCode(srchKeyCode)
            .searchWrd(searchWrd)
            .arrgOrd(arrgOrd)
            .build();

        return programRetrieveClient.getList(programListRequestDto);
    }


    private String getToken() throws IOException {

        URL url = new URL("https://openapi.vito.ai/v1/authenticate");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("accept", "application/json");
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setDoOutput(true);

        byte[] out = ("client_id=" + clientId + "&" + "client_secret=" + clientSecret).getBytes(
            StandardCharsets.UTF_8);

        OutputStream stream = httpConn.getOutputStream();
        stream.write(out);

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
            ? httpConn.getInputStream()
            : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        s.close();

        JsonNode jsonNode = objectMapper.readTree(response);
        log.info("access_token={}", jsonNode.get("access_token").asText());
        return jsonNode.get("access_token").asText();


    }

    private String postTranscribe(String token, File file) throws Exception {
        URL url = new URL("https://openapi.vito.ai/v1/transcribe");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("accept", "application/json");
        httpConn.setRequestProperty("Authorization", "Bearer " + token);
        httpConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=authsample");
        httpConn.setDoOutput(true);

        DataOutputStream outputStream;
        outputStream = new DataOutputStream(httpConn.getOutputStream());

        outputStream.writeBytes("--authsample\r\n");
        outputStream.writeBytes(
            "Content-Disposition: form-data; name=\"file\";filename=\"" + file.getName()
                + "\"\r\n");
        outputStream.writeBytes(
            "Content-Type: " + URLConnection.guessContentTypeFromName(file.getName()) + "\r\n");
        outputStream.writeBytes("Content-Transfer-Encoding: binary" + "\r\n");
        outputStream.writeBytes("\r\n");

        FileInputStream in = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        int bytesRead = -1;
        while ((bytesRead = in.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes("--authsample\r\n");
        }
        outputStream.writeBytes("\r\n");
        outputStream.writeBytes("--authsample\r\n");
        outputStream.writeBytes("Content-Disposition: form-data; name=\"config\"\r\n");
        outputStream.writeBytes("Content-Type: application/json\r\n");
        outputStream.writeBytes("\r\n");
        outputStream.writeBytes("{\n  \"diarization\": {\n");
        outputStream.writeBytes("	\"use_verification\": false\n");
        outputStream.writeBytes("	},\n");
        outputStream.writeBytes("\"use_multi_channel\": false,\n");
        outputStream.writeBytes("\"use_itn\": false,\n");
        outputStream.writeBytes("\"use_disfluency_filter\": false,\n");
        outputStream.writeBytes("\"use_profanity_filter\": false,\n");
        outputStream.writeBytes("\"paragraph_splitter\": {\n");
        outputStream.writeBytes("	\"min\": 10,\n");
        outputStream.writeBytes("	\"max\": 50\n");
        outputStream.writeBytes("	}\n");
        outputStream.writeBytes("}");
        outputStream.writeBytes("\r\n");
        outputStream.writeBytes("--authsample\r\n");
        outputStream.flush();
        outputStream.close();

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
            ? httpConn.getInputStream()
            : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        s.close();

        JsonNode jsonNode = objectMapper.readTree(response);
        log.info("id = {}", jsonNode.get("id").asText());
        return jsonNode.get("id").asText();
    }

    private String getTranscribe(String token, String id) throws IOException {
        URL url = new URL("https://openapi.vito.ai/v1/transcribe/" + id);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");
        httpConn.setRequestProperty("accept", "application/json");
        httpConn.setRequestProperty("Authorization", "Bearer " + token);

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
            ? httpConn.getInputStream()
            : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";


    }

    private boolean isValidWavFile(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            byte[] header = new byte[12];
            if (is.read(header) != header.length) {
                // File is too short
                return false;
            }

            // Check the "RIFF" and "WAVE" signatures
            return header[0] == 'R' && header[1] == 'I' && header[2] == 'F' && header[3] == 'F'
                && header[8] == 'W' && header[9] == 'A' && header[10] == 'V' && header[11] == 'E';
        } catch (IOException ex) {
            // An error occurred while reading the file
            return false;
        }
    }

}
