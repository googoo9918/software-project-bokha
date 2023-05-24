package com.app.domain.welfare.controller;

import static com.app.domain.common.ApiResult.*;

import com.app.domain.common.ApiResult;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import com.app.domain.program.dto.ProgramDto.ListResponse;
import com.app.domain.welfare.service.WelfareService;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/programs")
public class WelfareController {


    private final WelfareService welfareService;

    private final MemberService memberService;


    @PostMapping("/searchByVoice")
    public ApiResult<ListResponse> uploadFile(@RequestParam("file") MultipartFile file,
        HttpServletRequest httpServletRequest) throws Exception {
        if (memberService.checkLogin(httpServletRequest)){
            Member loginMember = memberService.getLoginMember(httpServletRequest);
             return OK(welfareService.searchWelfareByVoice(file,loginMember));
        }else {
            return OK(welfareService.searchWelfareByVoice(file,null));
        }
    }


}
