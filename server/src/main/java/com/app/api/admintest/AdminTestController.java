package com.app.api.admintest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 인가 작업 test용 컨트롤러
 *
 */
@RestController
@RequestMapping("/api/admin")
public class AdminTestController {

    @GetMapping("/test")
    public String adminTest() {
        return "admin test success";
    }

}
