package com.app.domain.member.entity;

import com.app.domain.common.BaseEntity;
import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.constant.Status;
import com.app.global.jwt.dto.JwtTokenDto;
import com.app.global.util.DateTimeUtils;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MemberType memberType;
    // 카카오 회원인지, 네이버 회원인지 Enum으로 관리
    @Column(unique = true, length = 50, nullable = false)
    private String email;

    @Column(length = 200)
    private String password;
    // 소셜 로그인 인증 같은 경우 비밀번호를 직접 다루지 않기 때문에 nullable
    @Column(nullable = false, length = 20)
    private String memberName;

    @Column(length = 10)
    private int age;
    //회원 나이 저장

    @Column(length = 50)
    private String region;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role;
    @Enumerated(EnumType.STRING)
    @Column
    private Status status;
    @Column(length = 250)
    private String refreshToken;
    // 리프레쉬 토큰
    private LocalDateTime tokenExpirationTime;
    // 토큰 만료 시간
    @Builder
    public Member(MemberType memberType, String email, String password, String memberName,
                  int age, String region, Role role) {
        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.age = age;
        this.region = region;
        this.role = role;
    }

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }
}
