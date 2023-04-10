package com.app.api.exceptiontest.controller;

import com.app.api.exceptiontest.dto.BindExceptionTestDto;
import com.app.api.exceptiontest.dto.TestEnum;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exception")
public class ExceptionTestController {

    @GetMapping("/bind-exception-test")
    public String bindExceptionTest(@Valid BindExceptionTestDto bindExceptionTestDto) {
        return "ok";
//       params에 key: value2 / Value : 20 입력 시
//       400 Bad Request
//       [value1] 해당 값은 필수 입력값입니다.
//       [value2] "최대 입력값은 10입니다" 에러 발생
    }

    @GetMapping("/type-exception-test")
    public String typeMismatchException(TestEnum testEnum) {
        return "ok";
//       key : testEnum Value : TestEnum에 있는 TEST를 보낼 경우 정상 작동
//       TestEnum 값에 없는 값을 보내주면 에러 발생
    }

    @GetMapping("/business-exception-test")
    public String businessExceptionTest(String isError) {
//       key : isError Value : true로 전송 시 비즈니스 로직 에러 발생
        if("true".equals(isError)) {
            throw new BusinessException(ErrorCode.TEST);
//      500 Interner Server status
//      에러코드 enum에서 정의한 에러코드 전송됨(errorCode: 001 / errorMessage : business exception test)
        }
        return "ok";
    }

    @GetMapping("/exception-test")
    public String exceptionTest(String isError) {
//      key : isError Value : true로 전송 시 500 Interner Server 에러 발생
//      errorCode: 500 Interner Server error / errorMessage : 예외 테스트
        if("true".equals(isError)) {
            throw new IllegalArgumentException("예외 테스트");
        }

        return "ok";
    }

}
