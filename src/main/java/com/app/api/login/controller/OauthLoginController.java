package com.app.api.login.controller;

import com.app.api.login.dto.OauthLoginDto;
import com.app.api.login.service.OauthLoginService;
import com.app.api.login.validator.OauthValidator;
import com.app.domain.member.constant.MemberType;
import com.app.global.util.AuthorizationHeaderUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 클라이언트로부터 소셜 로그인을 받는 컨트롤러
 * localhost:8080/api/oauth/login
 * {
 *     Body : "memberType" : "KAKAO"만 있고 Authorization 안붙이는 경우 -> "Authorization Member가 빈값입니다"
 *     Authrorization과 같이 보내는데 앞에 Bearer 안붙이는 경우 -> "인증 타입이 Bearer 타입이 아닙니다"
 *
 *     grantType, accessToken, accessTokenExpireTime, refreshToken, refreshTokenExpireTime 반환
 *
 * }
 */
@Tag(name = "authentication", description = "로그인/로그아웃/토큰재발급 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
public class OauthLoginController {

    private final OauthValidator oauthValidator;
    private final OauthLoginService oauthLoginService;

    @Tag(name = "authentication")
    @Operation(summary = "소셜 로그인 API", description = "소셜 로그인 API")
    @PostMapping("/login")
    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request oauthLoginRequestDto,
                                                             HttpServletRequest httpServletRequest) {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        //헤더에 있는 토큰 정보를 가져옴
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader); //헤더 유효성 검증
        oauthValidator.validateMemberType(oauthLoginRequestDto.getMemberType()); //멤버타입 검증

        String accessToken = authorizationHeader.split(" ")[1]; //
        OauthLoginDto.Response jwtTokenResponseDto = oauthLoginService
                .oauthLogin(accessToken, MemberType.from(oauthLoginRequestDto.getMemberType()));
        return ResponseEntity.ok(jwtTokenResponseDto);
    }

}
