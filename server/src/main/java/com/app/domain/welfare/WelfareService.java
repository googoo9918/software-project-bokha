package com.app.domain.welfare;

import com.app.domain.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Service
@RequiredArgsConstructor
@Slf4j
public class WelfareService {


    private final ObjectMapper objectMapper;

    private final WordAnalysisService wordAnalysisService;


    public List<Welfare> searchWelfareByVoice(MultipartFile multipartFile) throws Exception {
        if (!isValidWavFile(multipartFile)) {
            throw new RuntimeException("WAV 형식의 파일이 아닙니다!");
        }

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
                    Thread.sleep(10000);
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
        String parseWelfare = callWelfareApi(searchWrd);
        List<Welfare> welfareList = parseWelfareXml(parseWelfare);

        return welfareList;

    }

    private String callWelfareApi(String searchWrd) {

        StringBuilder urlBuilder = new StringBuilder(
            "https://apis.data.go.kr/B554287/LocalGovernmentWelfareInformations/LcgvWelfarelist");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8)
            + "=AeXDUH1p76XogolMY0RiiAfGZEvBXlMLm6q5%2FwE9NqSid7KE4CtaiTIlaRTSPmuU9EsIOFFkO0r7ES1hY%2Fo1ag%3D%3D");
        urlBuilder.append(
            "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1",
                StandardCharsets.UTF_8));
        urlBuilder.append(
            "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(
                "10", StandardCharsets.UTF_8));
        urlBuilder.append(
            "&" + URLEncoder.encode("lifeArray", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(
                "005,006", StandardCharsets.UTF_8));
        urlBuilder.append("&" + URLEncoder.encode("srchKeyCode", StandardCharsets.UTF_8) + "="
            + URLEncoder.encode("003", StandardCharsets.UTF_8));
        urlBuilder.append(
            "&" + URLEncoder.encode("searchWrd", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(
                searchWrd, StandardCharsets.UTF_8));
        urlBuilder.append(
            "&" + URLEncoder.encode("arrgOrd", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(
                "001", StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "*/*;q=0.9");

            BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8"));

            String line;
            while ((line = br.readLine()) != null) {
                result.append(line);
            }

            br.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("result = {}", result.toString());
        return result.toString();
    }

    private List<Welfare> parseWelfareXml(String xmlData) {
        List<Welfare> welfareList = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputStream is = new ByteArrayInputStream(xmlData.getBytes("UTF-8"));
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("servList");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    welfareList.add(Welfare.Of(eElement));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return welfareList;
    }

    private String getToken() throws IOException {

        String clientId = "YLAaayqPvReeb7J3YkOM";
        String clientSecret = "3TscXARPyk2l3cNpH1BONW_R43EkkHkbR6JSviA0";

        URL url = new URL("https://openapi.vito.ai/v1/authenticate");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("accept", "application/json");
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setDoOutput(true);

        String data = "client_id=YLAaayqPvReeb7J3YkOM&client_secret=3TscXARPyk2l3cNpH1BONW_R43EkkHkbR6JSviA0";
        byte[] out = data.getBytes(StandardCharsets.UTF_8);

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
        String response = s.hasNext() ? s.next() : "";

//        JsonNode jsonNode = objectMapper.readTree(response);
//        log.info("response = {}",response);
//        JsonNode utterancesNode = jsonNode.get("results").get("utterances");
//        StringBuilder result = new StringBuilder();;
//        for (JsonNode utterance : utterancesNode) {
//            String msg = utterance.get("msg").asText();
//            result.append(msg).append(" ");
//        }
//
//        log.info("msg={}",result.toString());
//
//        return result.toString();

        return response;

    }

    private boolean isValidWavFile(MultipartFile file){
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