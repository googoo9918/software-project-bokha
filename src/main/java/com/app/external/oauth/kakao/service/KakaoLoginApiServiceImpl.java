package com.app.external.oauth.kakao.service;

import com.app.domain.member.constant.MemberType;
import com.app.external.oauth.kakao.client.KakaoUserInfoClient;
import com.app.external.oauth.kakao.dto.KakaoUserInfoResponseDto;
import com.app.external.oauth.model.OAuthAttributes;
import com.app.external.oauth.service.SocialLoginApiService;
import com.app.global.jwt.constant.GrantType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * SocialLoginApiService를 implements, 카카오를 위한 로그인 서비스 구현체 착성
 * getUserInfo를 카카오 개발자 센터에서 가져오는 정보에 맞게 작성
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class KakaoLoginApiServiceImpl implements SocialLoginApiService {

    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final String CONTENT_TYPE = "application/x-www-form-urlencoded;charset=utf8";

    @Override
    public OAuthAttributes getUserInfo(String accessToken) {
        KakaoUserInfoResponseDto kakaoUserInfoResponseDto = kakaoUserInfoClient.getKakaoUserInfo(CONTENT_TYPE,
                GrantType.BEARER.getType() + " " + accessToken); // 카카오 회원 정보를 불러(개발자 센터를 통해)온 후 저장
        KakaoUserInfoResponseDto.KakaoAccount kakaoAccount = kakaoUserInfoResponseDto.getKakaoAccount();
        String email = kakaoAccount.getEmail();

        return OAuthAttributes.builder()
                .email(!StringUtils.hasText(email) ? kakaoUserInfoResponseDto.getId() : email) //이메일이 없는 경우 카카오 id 저장
                .name(kakaoAccount.getProfile().getNickname()) //닉네임 가져옴
                .profile(kakaoAccount.getProfile().getThumbnailImageUrl()) //이미지 가져옴
                .memberType(MemberType.KAKAO) //멤버타입 카카오로 설정
                .build();
    }

}
