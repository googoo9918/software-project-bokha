package com.app.api.tokentest;

import com.app.domain.member.constant.Role;
import com.app.global.jwt.dto.JwtTokenDto;
import com.app.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/token-test")
@RequiredArgsConstructor
public class TokenTestController {

    private final TokenManager tokenManager;

    @GetMapping("/create")
    // token 생성 확인 컨트롤러
    public JwtTokenDto createJwtTokenDto() {
        return tokenManager.createJwtTokenDto(1L, Role.ADMIN);
    }
    // ResponseBody 내용
    // grantType : Bearer
    // AccessToken, accessTokenExpireTime, refreshToken, refreshTokenExpireTime
    @GetMapping("/valid")
    //token 유효성 확인 컨트롤러
    // postman에서 위에서 만들었던 accessToken을 requestParams 방식으로 전달
    public String validateJwtToken(@RequestParam String token) {
        tokenManager.validateToken(token);
        Claims tokenClaims = tokenManager.getTokenClaims(token);
        Long memberId = Long.valueOf((Integer) tokenClaims.get("memberId"));
        String role = (String) tokenClaims.get("role");
        log.info("memberId : {}", memberId);
        log.info("role : {}", role);
        return "success";
    }

}
