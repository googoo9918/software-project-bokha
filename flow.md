### 목차
- [web 패키지](#web-패키지)
  - [카카오 로그인(회원가입)](#카카오-로그인회원가입)
- [api 패키지](#api-패키지)
  - [발급받은 카카오 Access 토큰 기반 로그인(회원가입)](#발급받은-카카오-access-토큰-기반-로그인회원가입)
  - [로그아웃](#로그아웃)
  - [회원정보조회](#회원정보조회)
  - [Access Token 재발급](#access-token-재발급)
- [domain 패키지](#domain-패키지)
  - [Auditable(BaseEntity, BaseTimeEntity)](#auditablebaseentity-basetimeentity)
  - [Member 패키지](#member-패키지)
- [external/oauth 패키지](#externaloauth-패키지)
  - [kakao 패키지](#kakao-패키지)
  - [Model 패키지](#model-패키지)
  - [Service 패키지](#service-패키지)
- [global 패키지](#global-패키지)
  - [config/jpa 패키지](#configjpa-패키지)
  - [config/web 패키지](#configweb-패키지)
  - [config/나머지 클래스](#config나머지-클래스)
    - [FeignConfiguration](#feignconfiguration)
    - [JasyptConfig](#jasyptconfig)
    - [SecurityConfig](#securityconfig)
    - [SwaggerConfig](#swaggerconfig)
  - [error/exception 패키지](#errorexception-패키지)
  - [error/ 나머지 클래스](#error-나머지-클래스)
    - [ErrorCode](#errorcode)
    - [ErrorResponse](#errorresponse)
    - [FeignClientExceptionErrorDecoder](#feignclientexceptionerrordecoder)
    - [GlobalExceptionHandler](#globalexceptionhandler)
  - [interceptor 패키지](#interceptor-패키지)
    - [AdminAuthorizationInterceptor](#adminauthorizationinterceptor)
    - [AuthenticationInterceptor](#authenticationinterceptor)
  - [jwt 패키지](#jwt-패키지)
    - [constant 패키지](#constant-패키지)
    - [dto 패키지](#dto-패키지)
    - [service 패키지](#service-패키지-1)
  - [resolver 패키지](#resolver-패키지)
    - [memberinfo 패키지](#memberinfo-패키지)
  - [Util 패키지](#util-패키지)
  - [appication.yml](#appicationyml)

### 패키지 구조 설명

![image](https://user-images.githubusercontent.com/102513932/211573608-394544b5-f68f-47f9-8842-15c46c492d27.png)

# web 패키지
- web 패키지를 제외하고는 패키지 이름 순서대로 설명 기술
## 카카오 로그인(회원가입)
- 전체 flow
    - ![image](https://user-images.githubusercontent.com/102513932/211477661-c1da4255-69bf-4bd8-b20e-751b29bfb828.png)
- web/kakotoken/controller login() 메서드 -> loginForm.html 페이지 불러옴
    - ![image](https://user-images.githubusercontent.com/102513932/211471652-997811e5-e055-4d4f-aaab-a15b8fe9fd2d.png)
- 카카오 로그인 버튼 클릭(2. 카카오 로그인 페이지 요청) -> /oauth/kakao/callback 리다이렉트(3.카카오 로그인 페이지 제공) -> requestKaKaoToken(43줄) 메서드 실행(4.카카오 계정 로그인) -> 토큰 받아와서 -> kakaoToken에 저장됨(5.Authorization code 발급)
    - ![image](https://user-images.githubusercontent.com/102513932/211471813-0a1e8022-8d5b-4bde-8cc9-802eb5a4122f.png)
    - ![image](https://user-images.githubusercontent.com/102513932/211472214-9578d322-ea35-4277-b7ff-9fd71e77621b.png)
        - response 데이터 내용

# api 패키지
## 발급받은 카카오 Access 토큰 기반 로그인(회원가입)
- 위에서 발급받은 카카오 토큰을 기반으로 로그인 진행
    - 36번째 줄 socialLoginApiservice.getUserInfo
- api/login/contorller oauthLogin 메서드
    - **주의 MemberType이 String 값으로 requestBody에 포함되야함**
    - **주의 Authrization 헤더 앞에 Bearer이 포함되어야 함**
        - ex) `Bearer QFshOekL_lGgWR_vEyilaFSjUl2MLoxy5peeflcJCisNHgAAAYWaPSak`
- `validateAuthorization`, `validateMemberType` 을 통해 토큰, MemberType(kakao) 유효성 검증
- `oauthLogin`를 통해 레포에 회원 정보 저장 (OauthLoginService에서 로직 진행)
    - ![image](https://user-images.githubusercontent.com/102513932/211491008-e5d20221-ca3d-4806-a0fd-8e44229075d6.png)
    - ![image](https://user-images.githubusercontent.com/102513932/211491055-5b606828-4df0-46cb-ae78-daf06f7a82aa.png)

## 로그아웃
- api/logout/controller logout 메서드
- `validateAuthorization` 통해 토큰 유효성 검증
- ![image](https://user-images.githubusercontent.com/102513932/211491779-0792dbeb-c63b-4264-8efb-ebc6b970699a.png)
    - 로그아웃 시 현재 시간으로 refresh 토큰 만료시간 설정
    - LogoutService에서 로직 진행

## 회원정보조회
- api/member/controller getMemberInfo 메서드
    - 로그인한 토큰값을 통해서 현재 로그인한 회원의 정보를 불러옴 (프로젝트에서 굳이 필요한가? 사용된다면 마이페이지 등에서 이용)
- global/resolver/memberinfo 패키지에서 MemberInfo 어노테이션 제작
    - MemberInfoArgumentResolver(memberInfo 구현부)에서 토큰에 있는 정보를 MemberInfoDto로 변환 후 파라미터 값으로 넣어줌
    - MemberInfoDto에서 MemberId값 받아서 service단에서 회원 정보 세팅해주고, 반환해줌
- ![image](https://user-images.githubusercontent.com/102513932/211494424-548b9db8-fd03-4787-bffc-68ee741edc3a.png)

## Access Token 재발급
- ![image](https://user-images.githubusercontent.com/102513932/211496523-fed91425-8d4a-4058-ad9c-9b201b2d0d0c.png)
- api/token/controller createAccessToken 메서드
    - 로그인 시 발급받은 refreshToken을 통해 재발급
- ![image](https://user-images.githubusercontent.com/102513932/211496661-c123bd68-2c15-4a2a-a74a-0e552c91da54.png)

# domain 패키지
## Auditable(BaseEntity, BaseTimeEntity)
- domain/common/ 패키지에 BaseEntity, BaseTimeEntity 구현
    - BaseEntity는 엔티티가 생성 및 수정된 엔드포인트의 값 저장 ex) /api/oauth/login
    - BaseTimeEntity는 엔티티 생성 및 수정 시간 저장
- global/config/jpa JpaConfig 파일의 @EnableJpaAuditing 어노테이션 을 통해 auditing 사용 가능 및 구현체 설정 및 빈 등록
- global/config/jpa AuditorAwareImpl 클래스를 통해 BaseEntity에서 uri정보 추출 가능

## Member 패키지
- constant 패키지에는 어느 소셜 로그인을 통해 로그인 하는지에 대한 MemberType값이 enum 타입으로 들어가 있음
    - ![image](https://user-images.githubusercontent.com/102513932/211540799-9cea419e-0cba-4522-8cbe-464dcd55669b.png)
    - 사진과 같은 Naver 인증 추가에 대한 다형성 대비
- Role 패키지에는 유저 권한 설정에 대한 값이 enum으로 들어가 있음
    - 추후 인가 설정에서 사용 가능 예정(**상담사 추가 요망**)
- entity 패키지에는 유저 엔티티에 대한 필드가 들어가있음
- repository 패키지 설명 생략
- service 패키지 설명 생략

# external/oauth 패키지
## kakao 패키지
- ![image](https://user-images.githubusercontent.com/102513932/211540799-9cea419e-0cba-4522-8cbe-464dcd55669b.png)
    - 회원 로그인은 위 사진의 흐름대로 이뤄짐
    - 로그인 진행 시 api/login/service의 OauthLoginService에서  getSocialLoginApiServiceMemberType(33번째 줄)을 이용하여 oauth/service  패키지의 SocialLoginApiService 구현체를 가져옴
        - 현재는 KakaoLoginApiServiceImpl
    - getUserInfo(36번째 줄)을 통해 회원 정보를 가져옴
        - 현재 KakaoLoginApiServiceImpl에서 구현되어 있음
- Client 패키지의 KakaoUserinfoClient는 KaKoLoginApiServiceImpl 에서 카카오 개발자 센터를 통해 유저 정보를 가져오기 위해 FeignClient로 구현되어 있음
- dto 패키지의 KakaoUserInfoResponseDto는 카카오 개발자 센터 기준으로 작성됨
- service 패키지의 KakaoLoginApiService는 회원 정보를 가져와 통일한 형식(OAuthAttributes)로 변환해줌

## Model 패키지
- OAuthAttributes는 각 개발자 센터에서 가져오는 회원 정보의 반환 형태가 다 다르니 통일된 규격을 통해 홈페이지 회원가입을 용이하게 함

## Service 패키지
- socialLoginApiService는 다형성을 대비한 interface, KakaoLoginApiService의 부모 클래스임
- socialLoginApiServiceFactory는 socialLoginApiService의 구현체들을 정리하고 멤버 타입에 맞춰 구현체를 반환해주는 클래스임

# global 패키지
## config/jpa 패키지
- 앞서 auditable에서 설명했듯이, AuditorAwareImpl은 BaseEntity에서 생성자와 수정자에 API request uri를 설정하기 위한 클래스
- JpaConfig는 AuditorAwareImpl을 사용하기위한 설정 파일

## config/web 패키지
- WebConfig
- addCorsMappings 메서드
    - cors 적용 메서드
    - ![image](https://user-images.githubusercontent.com/102513932/211573977-89aac8d1-c077-42aa-b325-d8e659f52ac9.png)
- addInterceptors
    - 토큰 검증과 같은 인증과, Member_Role에 해당하는 인가 인터셉터 적용 메서드
    - 인증은 예외 uri 작성
    - 인가는 포함 uri 작성(**상담사 부분 추가 요망**)
- addArgumentResolvers
    - 갖고있는 유저의 정보를 컨트롤러 클래스 메소드의 파라미터로 받을 수 있도록 설정(MemberInfoArgumentResolver을 위한 설정)

## config/나머지 클래스
### FeignConfiguration
- feign 설정 파일을 만들어 로깅레벨과 구현한 errorDecoder, 재시도를 위한 Retryer를 빈으로 등록
- 카카오 토큰 발급 시(로그인), 카카오 유저 정보 받아올 시 사용하는 Feign을 위한 설정 클래스

### JasyptConfig
- yml 암호화를 위한 설정 파일
- 팀원들끼리 **Vm Options에서 같은 비밀번호를 공유해야함**
- yml 파일에 들어가 있는 카카오 client id/password , db 패스워드, token secret key 등에 사용
- `-Djasypt.password=sakncksjallkasdkl#$@^#*asdsiajodias2737`
- test/app/JasyptTest
    - yml 암호화 진행 구현부 -> test를 통해서 yml 암호화 진행(복사 -> 붙여넣기)

### SecurityConfig
- 토큰 생성 및 사용에 필요한 application.yml에서 설정한 값들을 빈으로 등록함
- tokenSecret, accessTokenExpirationTime, refreshTokenExpirationTime

### SwaggerConfig
- Swagger 사용 시 설정 정보 등록 클래스
- ![image](https://user-images.githubusercontent.com/102513932/211581387-0541e62d-f6d4-4a9b-9aff-88bbb659f131.png)

## error/exception 패키지
- `AuthenticationException`, `EntityNotFoundException`는 BusinessException 상속
    - 인증처리 관련 에러와 엔티티 발생 에러 클래스 분류
- `BusinessException`는 `RuntimeException` 상속
    - 비즈니스 로직을 수행하다 조건이 맞지 않을 경우 BusinessException 발생
    - 사용할 CustomException 정의

## error/ 나머지 클래스
### ErrorCode
-  반환할 http status 값, 에러 코드, 에러메세지를 관리하는 Enum 클래스
    -  위에서 설정한 클래스 별 에러코드 분류 요망
### ErrorResponse
- 클라이언트 쪽으로 반환할 ErrorResponse
    - 에러코드와 에러메시지 반환

### FeignClientExceptionErrorDecoder
- @FeignClient 사용 시 발생하는 예외 처리
    - 문서 설명 주석 참고

### GlobalExceptionHandler
- @RestController에서 발생하는 전역적으로 발생하는 예외를 처리
    - 문서 설명 주석 참고

## interceptor 패키지
### AdminAuthorizationInterceptor
- 회원의 role을 검증하는 인가 인터셉터
    - 컨트롤러 로직을 수행하기 전에 먼저 수행되는 preHandle 메서드 구현(관련 설정 WebConfig 참고)

### AuthenticationInterceptor
- 회원의 토큰을 검증하는 인증 인터셉터
    - 컨트롤러 로직을 수행하기 전에 먼저 수행되는 preHandle 메서드 구현(관련 설정 WebConfig 참고)

## jwt 패키지
### constant 패키지
- GrantType
    - 토큰의 정보 설정을 위한 enum
- TokenType
    - 토큰의 역할 분류를 위한 enum

### dto 패키지
- JwtTokenDto
    - 토큰 정보 저장을 위한 클래스

### service 패키지
- TokenManager
    - 토큰 생성 및 유효성 확인, 만료시간 리턴에 대한 메서드 포함 클래스

## resolver 패키지
### memberinfo 패키지
- MemberInfo
    - 어노테이션 생성
- MemberInfoArgumentResolver
    - @MemberInfo에 대한 구현부
    - 토큰에 있던 정보를 MemberInfoDto로 만들어 전달
- MemberInfoDto
    - 반환 데이터 dto 생성

## Util 패키지
- AuthorizationHeaderUtils
    - authorizationHeader에 값이 들어있는지, Bearer 타입이 맞는지 확인
- DateTimeUtils
    - Date -> LocalDateTime으로 변환하는 메서드
    - Member 엔티티에 있는 tokenExpirationTime과 jwtTokenDto에 있는 refreshTokenExpireTime 타입을 맞춰주기 위한 클래스

## appication.yml
```yml
#default 프로젝트 설정 값
# 실행하는 profile에 따라 yml에 default로 설정해둔 값이 오버라이드 됨
server:
  port: 8080
  servlet:
    context-path: /


spring:
  # todo 개발 데이터베이스 연결
  datasource:
    url: jdbc:h2:tcp://localhost/~/test
    username: sa
    password:
    driver-class-name: org.h2.Driver


  jpa:
    hibernate:
      ddl-auto: create # 애플리케이션 실행 시 기존 테이블 삭제 후 테이블 새로 생성
      # 추후 validate나 none으로 변경할 것
    show-sql: true # 콘솔창에 sql 출력
    properties:
      hibernate:
        format_sql: true # sql 예쁘게 출력
        default_batch_fetch_size: 500 # 일대다 컬렉션 조회 시 성능 최적화
        # 쿼리에서 조건문 in절로 수행하게됨, n번 쿼리 수행 -> 1번 쿼리에서 수행(n개를 파라미터로 넣어줌)
        # DB와 애플리케이션 부하를 고려해서 개수를 설정할 것
        # oracle의 경우 최대 1000개
    open-in-view: false # 영속성 컨텍스트의 생존 범위를 트랜잭션 범위로 한정
    # 기본값 true -> View 랜더링 또는 API 요청일 경우 클라이언트 응답까지 영속성 컨텍스트 생존
    # false 설정 시 -> 생존 범위가 트랜잭션 범위로 줄어들게 됨
      # -> 컨트롤러에서 지연로딩으로 연관된 엔티티를 가져오려고 하면 에러 발생
    # 영속성 컨텍스트를 오래 유지하면 DB Connection도 오래 갖고 있으므로 DB Connection이 부족할 수 있음
      # -> 실시간 트래픽이 중요한 API 애플리케이션에서는 false로 유지할 것
  servlet:
    multipart:
      max-file-size: 10MB # 파일 업로드 요청 시 하나의 파일 크기를 10MB 제한
      max-request-size: 100MB # 파일 업로드 요청 시 모든 파일 크기합을 100MB 제한

  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher
#     swagger 사용을 위한 설정

logging:
  level:
    org.hibernate.type: debug # 콘솔창에 조건에 바인딩되는 값 및 조회 결과 출력
    com.app: debug # todo 패키지 수정
#    spring cloud openfeign(외부 API 호출을 쉽게 할 수 있도록 도와줌)
#    의 로그를 보기 위해서 프로젝트 루트 패키지 기준으로 debug 설정

feign:
  client:
    config:
      default:
        connectTimeout: 5000
        readTimeout: 5000
# 3-way-handshake 과정의 시간 설정(5초)
# 연결은 됐는데 요청한 서버에서 응답이 5초내로 오지 않으면 에러 발생(readTimeout)
kakao:
  client:
    id: ENC(2VWXhZZPewn1zK6e3ODVGcHbYJLRUmCw6zd6oc4ITu+V7LETI9sxmLLHsYbcP6s1)
#   RESTAPI 키를 복사
    secret: ENC(uWuzB+AjzEhpdi6RMDgNfJW/KPSkTfTPRsIECN3H09rUM3q2doWVr1WBDY/jUGtz)
#   보안 - 코드 값 복사

token:
  secret:  ENC(eIFwp/mynS6nbxDmqjNMzy+qVPlKB7H7LEk04Q85ggmsS34hVRDWl8BKiyg17HiF+KdNFuzF7S4=)
  access-token-expiration-time: 900000  # 15분 1000(ms) x 60(s) x 15(m)
  refresh-token-expiration-time: 1209600000 # 2주 1000(ms) x 60 (s) x 60(m) x 24(h) x 14(d)

jasypt:
  password: ${PASSWORD}
#  vmoption에 지정됨
```

  



