package com.app.api.health.controller;

import com.app.api.health.dto.HealthCheckResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Tag(name = "health check", description = "서버 상태 체크 API")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthCheckController {

    private final Environment environment;
    // 개발환경인지 운영환경인지 확인하기 위한 Environment Bean


    @Tag(name = "health check")
    @Operation(summary = "서버 Health Check API", description = "현재 서버가 정상적으로 기동이 된 상태인지 검사하는 API")
    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> healthCheck() {
        HealthCheckResponseDto healthCheckResponseDto = HealthCheckResponseDto.builder()
                .health("ok")
                .activeProfiles(Arrays.asList(environment.getActiveProfiles()))
                //현재 어떤 Profiles가 실행되는지 확인
                //Edit Configurations에서 추가 가능
                .build();
        return ResponseEntity.ok(healthCheckResponseDto);
        // 200 ok와 함께 만들어놓은 dto가 response body로 리턴됨

    }

}
