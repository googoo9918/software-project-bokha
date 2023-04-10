package com.app.api.feigntest.client;

import com.app.api.health.dto.HealthCheckResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Feign Cleint는 웹 서비스 클라이언트를 보다 쉽게 작성할 수 있도록 도와줌
 * interface를 작성하고 annotation을 붙여주면 세부적인 내용 없이 사용할 수 있음
 * 코드 복잡도 감소
 * ex) 컨트롤러 2개("/" , "/testfeign")를 한 서버에서 사용 시
 * 1. 브라우저가 http://localhost:8080으로 GET 요청
 * 2. Feign Client가 /testfeign에 GET 요청("/" -> "/testfeign")
 * 3. 컨트롤러가 결과값을 브라우저에 반환
 */
@FeignClient(url = "http://localhost:8080", name = "helloClient")
//호출할 url 정보와 name
//HealthFringTestConroller 참고
public interface HelloClient {

    @GetMapping(value = "/api/health", consumes = "application/json")
    HealthCheckResponseDto healthCheck();
// /api/health2와 같이 잘못된 url을 적을 경우 -> errorDecoder에서 에러 메시지(404) 반환
    // GlobalException에서 최종적으로 걸려 404 에러 반환
//  localhost:8082에 요청 보낼 시 -> connectTimeout에러 발생
    // 500번대 에러 -> 3번 Retrybing
}
