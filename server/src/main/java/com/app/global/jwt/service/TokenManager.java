package com.app.global.jwt.service;

import com.app.domain.member.constant.Role;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.jwt.constant.GrantType;
import com.app.global.jwt.constant.TokenType;
import com.app.global.jwt.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 토큰 생성 및 유효성 확인, 만료시간 리턴에 대한 메서드 포함 클래스
 */
@Slf4j
@RequiredArgsConstructor
public class TokenManager {

    private final String accessTokenExpirationTime;
    private final String refreshTokenExpirationTime;
    private final String tokenSecret;
    // 토큰을 생성할때 사용할 값
    // yml에서 설정한 값들을 가져올 것임

    public JwtTokenDto createJwtTokenDto(Long memberId, Role role) {
        // 토큰 생성
        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(memberId, role, accessTokenExpireTime);
        String refreshToken = createRefreshToken(memberId, refreshTokenExpireTime);
        return JwtTokenDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .build();
    }

    public Date createAccessTokenExpireTime() {
        //AccessToken 만료시간 리턴
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
        //현재 시간으로부터 15분(yml에서 설정)이 지나면 AccessToken이 만료된 것으로 확인
    }

    public Date createRefreshTokenExpireTime() {
        //RefreshToken 만료시간 리턴
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
        //현재시간으로부터 2주뒤의 시간 리턴
    }

    public String createAccessToken(Long memberId, Role role, Date expirationTime) {
        //AccessToken 생성
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name())    // 토큰 제목
                .setIssuedAt(new Date())                // 토큰 발급 시간
                .setExpiration(expirationTime)          // 토큰 만료 시간
                .claim("memberId", memberId)      // 회원 아이디
                .claim("role", role)              // 유저 role
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();
        return accessToken;
    }

    public String createRefreshToken(Long memberId, Date expirationTime) {
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name())   // 토큰 제목
                .setIssuedAt(new Date())                // 토큰 발급 시간
                .setExpiration(expirationTime)          // 토큰 만료 시간
                .claim("memberId", memberId)      // 회원 아이디
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();
        return refreshToken;
    }

    public void validateToken(String token) {
        // 토큰 유효성 확인 메서드
        try {
            Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            log.info("token 만료", e);
            throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

    public Claims getTokenClaims(String token) {
        // 토큰 정보를 서버에서 사용할 떄, payload에 있는 claim 정보들을 가져오는 메서드
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info("유효하지 않은 token", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
        return claims;
    }

}
