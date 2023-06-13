package com.app.external.oauth.model;

import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * 소셜 플랫폼에서 가져오는 회원 정보의 반환 형태가 다 다르니
 * 하나로 통일하여 회원가입 할 때 이 정보를 이용해 회원가입을 할 수 있게 진행함
 */
@ToString
@Getter @Builder
public class OAuthAttributes {

    private String name;
    private String email;
    private String profile;
    //프로필 사진 주소 저장
    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, Role role) {
        return Member.builder()
                .memberName(name)
                .email(email)
                .memberType(memberType)
//                .profile(profile)
                .role(role)
                .build();
    }

}
