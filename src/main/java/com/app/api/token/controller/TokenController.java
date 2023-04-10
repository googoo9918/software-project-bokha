package com.app.api.token.controller;

import com.app.api.token.dto.AccessTokenResponseDto;
import com.app.api.token.service.TokenService;
import com.app.global.util.AuthorizationHeaderUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Access Token 재발급 시 이용하는 API
 * http://localhost:8080/api/access-token/issue
 * 헤더에는 Bearer + refreshToken
 * 반환 값으로 grantType, accessToken, accessTokenExpireTime 확인 가능
 * refresh 토큰 만료 시 "해당 refresh token은 만료됐습니다" 에러 출력
 *
 */
@Tag(name = "authentication", description = "로그인/로그아웃/토큰재발급 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TokenController {

    private final TokenService tokenService;

    @Tag(name = "authentication")
    @Operation(summary = "Access Token 재발급 API", description = "Access Token 재발급 API")
    @PostMapping("/access-token/issue")
    public ResponseEntity<AccessTokenResponseDto> createAccessToken(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader); //헤더 유효성 검증

        String refreshToken = authorizationHeader.split(" ")[1];
        AccessTokenResponseDto accessTokenResponseDto = tokenService.createAccessTokenByRefreshToken(refreshToken);
        return ResponseEntity.ok(accessTokenResponseDto);
    }

}
