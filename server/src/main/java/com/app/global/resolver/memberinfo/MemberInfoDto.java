package com.app.global.resolver.memberinfo;

import com.app.domain.member.constant.Role;
import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class MemberInfoDto {

    private Long memberId;
    private Role role;

}
