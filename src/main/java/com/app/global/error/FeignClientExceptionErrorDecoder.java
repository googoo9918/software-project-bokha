package com.app.global.error;

import feign.FeignException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

/**
 * FeignClient 요청 시 200번 대 http status 코드를 반환 받을 경우 정상으로 판단
 * 서버로 요청했을 때 해당 서버에서 오류 반환할 수 있음 응답으로 받은
    * httpStatus가 200번대가 아니라면 에러가 났을 때 처리해줄 디코더를 만듬
 * 500번대 http status를 반환 받을 경우 재시도
 */
@Slf4j //로깅을 사용하기위한 애노테이션
public class FeignClientExceptionErrorDecoder implements ErrorDecoder {

    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.error("{} 요청 실패, status : {}, reason : {}", methodKey, response.status(), response.reason());
        FeignException exception = FeignException.errorStatus(methodKey, response);
        HttpStatus httpStatus = HttpStatus.valueOf(response.status());
        if(httpStatus.is5xxServerError()) {
//            500번대 에러의 경우 Retryable을 통해 재시도
            return new RetryableException(
                    response.status(),
                    exception.getMessage(),
                    response.request().httpMethod(),
                    exception,
                    null,
                    response.request()
            );
        }
        return errorDecoder.decode(methodKey, response);
//      Exception 반환
    }

}
