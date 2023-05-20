package com.app.domain.welfare;

import static com.app.domain.common.ApiResult.*;

import com.app.domain.common.ApiResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class WelfareController {


    private final WelfareService welfareService;



    @PostMapping("/upload")
    public ApiResult<List<Welfare>> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        List<Welfare> welfare = welfareService.searchWelfareByVoice(file);
        return OK(welfare);
    }


}
