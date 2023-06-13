package com.app.api.logout.controller;

import com.app.api.logout.service.LogoutService;
import com.app.global.util.AuthorizationHeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 로그아웃 시 DB에 저장된 refresh token 만료 처리
 * 다시 로그인 할 경우 refresh token 발급
 * http://localhost:8080/api/logout
 * Authorization 헤더에 추가
 * update 쿼리문 실행 -> 현재 시간 기준으로 토큰 만료 시간 업데이트
 * logout success 메시지 반환
 * 기존 refresh token으로 재로그인 불가능
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader); //헤더 유효성 검증

        String accessToken = authorizationHeader.split(" ")[1];
        logoutService.logout(accessToken);

        return ResponseEntity.ok("logout success");
    }

}
