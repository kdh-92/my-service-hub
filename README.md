# MSH (My Service Hub)
> 개발하고 싶었던 다양한 프로젝트를 멀티 모듈 형태로 구현해보는 프로젝트

# Project
## 가격비교 (코스트코, 트레이더스) `진행 예정`
## 통합 로그인 `진행 예정`

---

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
