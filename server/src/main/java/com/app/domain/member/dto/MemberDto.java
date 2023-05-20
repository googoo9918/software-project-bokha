package com.app.domain.member.dto;

import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.global.jwt.dto.JwtTokenDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Date;


public class MemberDto {

    @Getter
    public static class Post{

        @Email
        private String email;

        private String memberName;

        //        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@!%*#?&])[A-Za-z\\d@!%*#?&]{8,}$")
//        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
        private String password;

        private String confirmPassword;

        private int age;

        private String region;

    }

    @Getter
    public static class Login{
        //        @Pattern(regexp = "\t^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
        private String email;
        //        @Pattern(regexp = "\t^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")
        private String password;

        private String memberType;

    }
    @Getter
    @NoArgsConstructor
    public static class Patch{
        private Long memberId;

        private String password;

        private String newPassword;

        private String confirmNewPassword;

        private int age;

        private String region;

        public void updateMemberId(Long memberId){
            this.memberId = memberId;
        }

    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response{
        private Long memberId;

        private String email;

        private String password;

        private String memberName;

        private int age;

        private String region;

        private Role role;

        private MemberType memberType;

    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class MyPageResponse{
        private Long memberId;

        private String email;

        private String memberName;

        private int age;

        private String region;

        private String password;

    }
    @Getter @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LoginResponse {

        private String grantType;

        private String accessToken;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date accessTokenExpireTime;

        private String refreshToken;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date refreshTokenExpireTime;

        private Role role;
        public static LoginResponse of(JwtTokenDto jwtTokenDto, Role role){
            return LoginResponse.builder()
                    .role(role)
                    .grantType(jwtTokenDto.getGrantType())
                    .accessToken(jwtTokenDto.getAccessToken())
                    .accessTokenExpireTime(jwtTokenDto.getAccessTokenExpireTime())
                    .refreshToken(jwtTokenDto.getRefreshToken())
                    .refreshTokenExpireTime(jwtTokenDto.getRefreshTokenExpireTime())
                    .build();
        }
    }
}
