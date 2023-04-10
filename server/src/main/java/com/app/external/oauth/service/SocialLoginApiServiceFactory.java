package com.app.external.oauth.service;

import com.app.domain.member.constant.MemberType;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialLoginApiServiceFactory {

    private static Map<String, SocialLoginApiService> socialLoginApiServices;
    // map에는 SocialLoginApiService 구현체가 각각 들어갈 것임

    public SocialLoginApiServiceFactory(Map<String, SocialLoginApiService> socialLoginApiServices) {
        this.socialLoginApiServices = socialLoginApiServices;
    }

    public static SocialLoginApiService getSocialLoginApiService(MemberType memberType) {
        String socialLoginApiServiceBeanName = "";

        if(MemberType.KAKAO.equals(memberType)) { //멤버 타입이 카카오면
            socialLoginApiServiceBeanName = "kakaoLoginApiServiceImpl"; //빈 이름에 카카오 서비스 구현체 저장
        }
        return socialLoginApiServices.get(socialLoginApiServiceBeanName);
        // 빈 이름을 키 값으로 활용하여 구현체(KakaoLoginApiServiceImpl) 반환
    }

}
