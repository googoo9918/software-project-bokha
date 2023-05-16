package com.app.api.login.dto;

import com.app.global.jwt.dto.JwtTokenDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * 로그인 시 컨트롤러에서 사용할 response, request dto 작성
 */
public class OauthLoginDto {

    @Getter @Setter
    public static class Request {
        private String memberType;
    }

    @Getter @Setter
    @Builder @NoArgsConstructor @AllArgsConstructor
    public static class Response {

        private String grantType;

        private String accessToken;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date accessTokenExpireTime;

       private String refreshToken;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date refreshTokenExpireTime;

        public static Response of(JwtTokenDto jwtTokenDto) {
            return Response.builder()
                    .grantType(jwtTokenDto.getGrantType())
                    .accessToken(jwtTokenDto.getAccessToken())
                    .accessTokenExpireTime(jwtTokenDto.getAccessTokenExpireTime())
                    .refreshToken(jwtTokenDto.getRefreshToken())
                    .refreshTokenExpireTime(jwtTokenDto.getRefreshTokenExpireTime())
                    .build();
        }

    }

}
