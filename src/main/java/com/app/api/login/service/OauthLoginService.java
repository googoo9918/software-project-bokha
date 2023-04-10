package com.app.api.login.service;

import com.app.api.login.dto.OauthLoginDto;
import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.external.oauth.model.OAuthAttributes;
import com.app.external.oauth.service.SocialLoginApiService;
import com.app.external.oauth.service.SocialLoginApiServiceFactory;
import com.app.global.jwt.dto.JwtTokenDto;
import com.app.global.jwt.service.TokenManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 회원 로그인 서비스
 */
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OauthLoginService {

    private final MemberService memberService;
    private final TokenManager tokenManager;

    public OauthLoginDto.Response oauthLogin(String accessToken, MemberType memberType) {
        SocialLoginApiService socialLoginApiService = SocialLoginApiServiceFactory.getSocialLoginApiService(memberType);
        // membertype을 사용해 어떤 소셜 로그인 api 서비스를 사용할지 구현체를 받음

        OAuthAttributes userInfo = socialLoginApiService.getUserInfo(accessToken);
        // 회원 정보를 받아서 처리함

        log.info("userInfo : {}",  userInfo);

        JwtTokenDto jwtTokenDto;
        Optional<Member> optionalMember = memberService.findMemberByEmail(userInfo.getEmail());
        if(optionalMember.isEmpty()) { //기존 회원이 아닌경우
            Member oauthMember = userInfo.toMemberEntity(memberType, Role.USER); // Role 설정하여 멤버 생성
            oauthMember = memberService.registerMember(oauthMember); //회원가입

            // 토큰 생성
            jwtTokenDto = tokenManager.createJwtTokenDto(oauthMember.getMemberId(), oauthMember.getRole());
            oauthMember.updateRefreshToken(jwtTokenDto);
        } else { // 기존 회원일 경우
            Member oauthMember = optionalMember.get();

            // 토큰 생성
            jwtTokenDto = tokenManager.createJwtTokenDto(oauthMember.getMemberId(), oauthMember.getRole());
            oauthMember.updateRefreshToken(jwtTokenDto);
            // DB에 리프레쉬 토큰과 토큰 만료시간 업데이트
        }

        return OauthLoginDto.Response.of(jwtTokenDto);
        // grantType, accessToken, refreshToken, 만료시간 등 설정
    }

}
