package com.app.global.config;

import com.app.global.resolver.memberinfo.MemberInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

/**
 * 스웨거 설정 클래스
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30) //스웨거 설정에 핵심이 되는 Bean
                .select() // ApiSelectorBuilder 생성
                .apis(RequestHandlerSelectors.basePackage("com.app.api")) // API 패키지 경로(ex com.app.api) todo 패키지 경로 수정
                .paths(PathSelectors.ant("/api/**")) // path 조건에 따라서 API 문서화(ex /api/**) todo API 경로 수정
                .build()
                .apiInfo(apiInfo()) // API 문서에 대한 정보 추가(최상단부 제목 부분)
                .useDefaultResponseMessages(false) // swagger에서 제공하는 기본 응답 코드 설명 제거 (ex, 403 Forbidden)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                // security 설정으로 swagger에서 자물쇠 버튼을 클릭, Authrization 헤더를 넣어줄 수 있게됨
                .ignoredParameterTypes(MemberInfo.class)
                //현재 memberInfoController에서 Parameter는 직접 입력하는게 아니라 토큰에서 가져오는 값이기에 무시처리
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API 문서")
                .description("API에 대해서 설명해주는 문서입니다.")
                .version("1.0")
                .build();
    }


    /**
     * swagger에 authorizaion 헤더를 입력할 수 있게 제작
     * */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}