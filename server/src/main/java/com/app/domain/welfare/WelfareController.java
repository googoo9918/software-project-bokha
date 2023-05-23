package com.app.domain.welfare;

import static com.app.domain.common.ApiResult.*;

import com.app.domain.common.ApiResult;
import com.app.domain.member.entity.Member;
import com.app.domain.member.service.MemberService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class WelfareController {


    private final WelfareService welfareService;

    private final MemberService memberService;



    @PostMapping("/upload")
    public ApiResult<List<Welfare>> uploadFile(@RequestParam("file") MultipartFile file,
        HttpServletRequest httpServletRequest) throws Exception {
        if (memberService.checkLogin(httpServletRequest)){
            Member loginMember = memberService.getLoginMember(httpServletRequest);
            welfareService.searchWelfareByVoice(file,loginMember);
        }else {
            List<Welfare> welfare = welfareService.searchWelfareByVoice(file,null);
        }
        return OK(welfare);
    }


}
