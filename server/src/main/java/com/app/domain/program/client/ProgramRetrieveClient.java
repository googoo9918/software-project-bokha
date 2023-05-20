package com.app.domain.program.client;

import com.app.domain.program.dto.ProgramDto;
import com.app.global.config.XmlFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://apis.data.go.kr/B554287/LocalGovernmentWelfareInformations", name = "programRetrieveClient", configuration = XmlFeignConfiguration.class)
public interface ProgramRetrieveClient {

    @GetMapping(value = "LcgvWelfarelist",produces = MediaType.APPLICATION_XML_VALUE)
    ProgramDto.ListResponse getList(@SpringQueryMap ProgramDto.ListRequest listRequest);

    @GetMapping(value = "LcgvWelfaredetailed", produces = MediaType.APPLICATION_XML_VALUE)
    ProgramDto.DetailedResponse getDetailed(@SpringQueryMap ProgramDto.DetailRequest detailRequest);

}
