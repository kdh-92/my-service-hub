# MSH (My Service Hub)
> 개발하고 싶었던 다양한 프로젝트를 멀티 모듈 형태로 구현해보는 프로젝트

# Project
## 가격비교 (코스트코, 트레이더스) `진행 예정`
## 통합 로그인 `진행 예정`

## 사용 기술
> - Spring Boot 3.3.5
> - Kotlin
> - Java 17
> - MySQL

## 멀티 모듈 구조
- root - 필수 공통 Dependency
    - Auth (추후 통합 로그인 프로젝트로 분리)
        - 인증 & 인가
        - 로그인
        - 멤버
        - Spring Cloud Gateway
    - Market (App & Domain - 추후 분리할 수 있으면 분리)
    - Global 모듈
        - Custom Exception
        - Logger

---

### Custom Exception
- global에서 Custom Exception 처리를 위해 CustomExceptionHandler 적용 시 web 의존성이 발생하게 되었다.
- 다른 모듈에서 global 모듈의 의존성을 그대로 가져오게되면 모두 web을 가지게 되는 문제점 발생
- 또한 auth 모듈에서는 spring cloud gateway 사용을 위해 webflux로 설정이 필요한데 global 모듈의 web과 충돌이 발생
- 위 문제를 해결하기 위해 global - CustomExceptionHandler를 제거하고 각 모듈에 필요한 ExceptionHandler를 추가하는 방향으로 수정

### Auth
> Spring Cloud Gateway + Spring Security + Webflux
> - gateway - jwt 인증
> - spring security - OAuth2 로그인 (회원가입)
 
`Spring Security` - 모든 url에 대해 허용 후 로그인만 OAuth2 - google로 처리  
`Spring Cloud Gateway` - Security에서 필터 처리가 된 이후에 gateway에서 jwt 토큰 검증 후 로그인 처리 or 서비스 요청
 