package com.app.domain.program.client;

import com.app.domain.program.dto.ProgramDto;
import com.app.global.config.XmlFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://apis.data.go.kr/B554287/LocalGovernmentWelfareInformations", name = "programRetrieveClient", configuration = XmlFeignConfiguration.class)
public interface ProgramRetrieveClient {

    @GetMapping(value = "LcgvWelfarelist?serviceKey=AeXDUH1p76XogolMY0RiiAfGZEvBXlMLm6q5%2FwE9NqSid7KE4CtaiTIlaRTSPmuU9EsIOFFkO0r7ES1hY%2Fo1ag%3D%3D", produces = MediaType.APPLICATION_XML_VALUE)
    ProgramDto.Response getList(@SpringQueryMap ProgramDto.Request request);
}
