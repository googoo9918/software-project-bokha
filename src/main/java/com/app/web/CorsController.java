package com.app.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CorsController {

    @GetMapping("/cors")
    public String cors() {
        return "cors";
    }
    // cors 확인을 위한 컨트롤러, WebConfig 파일이 없으면 cors에러가 발생한다

}
