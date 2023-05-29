package com.app.global.config.web;

import com.app.global.interceptor.AdminAuthorizationInterceptor;
import com.app.global.interceptor.AuthenticationInterceptor;
import com.app.global.resolver.memberinfo.MemberInfoArgumentResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    private final MemberInfoArgumentResolver memberInfoArgumentResolver;
    private final AdminAuthorizationInterceptor adminAuthorizationInterceptor;
    private final ObjectMapper objectMapper;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") //어떤 url로 요청이 왔을 때 허용할 것인지
                .allowedOrigins("*")
                .allowedMethods(
                        //별표(*) 사용 시 모든 오리진 허용 및 콤마로 여러 origin을 설정할 수도 있음
                        //즉, locathost:8080로 시작하는 엔드포인트에서 /api/**으로 아래 요청을 보내는 것을 허용함
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name()
                )
                .maxAge(3600);
        //preplight는 교차출처 HTTP 요청 전 요청의 헤더와 메서드에 대해 인식하고 있는지 확인
        //계속 preplight를 포함한 두 번의 요청을 보내게 되면 성능 저하가 발생하기 때문에 시간 설정하여 한 번만 전송하게 만듬
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .order(1) //시행 순서 지정
                .addPathPatterns("/api/**") //인증 인터셉터가 어떤 api에 작동할지 지정
                .excludePathPatterns("/kakao/login", //카카오 로그인 버튼
                        "/api/logout", //로그아웃
                        "/api/access-token/issue", // Access 토큰 재발급
                        "/api/members/new", //회원가입
                        "/api/members/login", //자체 로그인
                        "/api/programs/list", // 프로그램 목록 조회
                        "/api/programs/detail", // 프로그램 상세 조회
                        "/api/post/list", //글 전체 조회
                        "/api/post/{post-id}", //글 상세 조회
                        "/api/programs/searchByVoice"
                ); // 인증 인터셉터를 동작시키지 않을 예외적인 uri 작성

        registry.addInterceptor(adminAuthorizationInterceptor) //인증 인터셉터 다음 인가 인터셉터 실행
                .order(2)
                .addPathPatterns("/api/post/post", //어떤 uri에 대해 인가 인터셉터를 동작하게 할 것인가?
                        "api/post/edit/{post-id}",
                        "api/post/delete/{post-id}");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(memberInfoArgumentResolver);
    }


}
