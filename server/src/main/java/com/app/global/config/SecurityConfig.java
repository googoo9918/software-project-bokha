package com.app.global.config;

import com.app.global.jwt.service.TokenManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    //토큰 생성 및 사용에 필요한, application.yml에서 설정한 값들을 빈으로 등록

    @Value("${token.access-token-expiration-time}")
    private String accessTokenExpirationTime;

    @Value("${token.refresh-token-expiration-time}")
    private String refreshTokenExpirationTime;

    @Value("${token.secret}")
    private String tokenSecret;

    @Bean
    public TokenManager tokenManager() {
        return new TokenManager(accessTokenExpirationTime, refreshTokenExpirationTime, tokenSecret);
    }

}
