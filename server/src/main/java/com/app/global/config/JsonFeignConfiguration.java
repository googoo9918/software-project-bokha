package com.app.global.config;

import com.app.global.error.FeignClientExceptionErrorDecoder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import feign.Logger;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;
import feign.jackson.JacksonDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
//@EnableFeignClients(basePackages = "com.app") // todo 패키지명 수정
//@Import(FeignClientsConfiguration.class)
public class JsonFeignConfiguration {
    @Bean
    public Decoder jcustomFeignDecoder() {
        return new JacksonDecoder();
    }

    @Bean
    Logger.Level jfeignLoggerLevel() {
        return Logger.Level.FULL;
    }
//    NONE : 로깅 X / BASIC : 요청 메서드와 URL, 응답 상태 코드, 실행시간을 로깅
//    HEADERS : BASIC 단계의 로깅에서 추가로 request와 response의 headers 로깅
//    FULL : request, response의 headers, body, metadata를 모두 로깅

    @Bean
    public ErrorDecoder jerrorDecoder() {
        return new FeignClientExceptionErrorDecoder();
    }

    @Bean
    public Retryer jretryer() {
        return new Retryer.Default(1000, 2000, 3);
    }
//    period : 실행 주기 / MaxPeriod : interval / maxAttemps : 최대 재시도 횟수

}
