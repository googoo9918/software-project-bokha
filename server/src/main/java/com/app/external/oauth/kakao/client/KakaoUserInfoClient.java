package com.app.external.oauth.kakao.client;

import com.app.external.oauth.kakao.dto.KakaoUserInfoResponseDto;
import com.app.global.config.JsonFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * 카카오 회원 정보를 가져오는 인터페이스
 * 카카오 REST API 사용자 정보 가져오기 참고
 * GET/POST /v2/user/me HTTP/1.1
 * Host: kapi.kakao.com
 * Authorization: Bearer ${ACCESS_TOKEN}/KakaoAK ${APP_ADMIN_KEY}
 * Content-type: application/x-www-form-urlencoded;charset=utf-8
 */
@FeignClient(url = "https://kapi.kakao.com", name = "kakaoUserInfoClient", configuration = JsonFeignConfiguration.class)
public interface KakaoUserInfoClient {

    @GetMapping(value = "/v2/user/me", consumes = "application/json")
    KakaoUserInfoResponseDto getKakaoUserInfo(@RequestHeader("Content-type") String contentType,
                                              @RequestHeader("Authorization") String accessToken);

}
