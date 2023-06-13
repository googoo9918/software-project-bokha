package com.app.domain.member.mapper;


import com.app.domain.member.dto.MemberDto;
import com.app.domain.member.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {


    MemberDto.MyPageResponse memberToMyPageResoponse(Member member);

    MemberDto.Response memberToMemberResponse(Member member);

    Member memberPostDtoToMember(MemberDto.Post memberPostDto);

    Member memberLoginDtoToMember(MemberDto.Login memberLoginDto);

    @Mapping(source = "newPassword", target = "password")
    Member memberPatchDtoToMember(MemberDto.Patch memberPatchDto);

    List<MemberDto.Response> membersToMemberMyPageResponses(List<Member> members);
}
