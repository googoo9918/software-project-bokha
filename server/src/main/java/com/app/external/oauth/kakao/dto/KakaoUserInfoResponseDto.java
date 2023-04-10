package com.app.external.oauth.kakao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 카카오 유저 정보 responseDto
 * 카카오 개발자 센터에서 확인한 정보들 작성
 */
@Getter @Setter
public class KakaoUserInfoResponseDto {

    private String id;
    //회원 번호
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;
    //카카오 계정 정보
    @Getter @Setter
    public static class KakaoAccount {
        private String email;
        // 카카오 계정 대표 이메일
        private Profile profile;
        // 프로필 정보
        @Getter @Setter
        public static class Profile {

            private String nickname;
            //닉네임
            @JsonProperty("thumbnail_image_url")
            private String thumbnailImageUrl;
            //썸네일 이미지 Url

        }

    }

}
