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
    - Application (Business)
    - Domain (도메인 모듈)
    - 내부 모듈
        - 환경별 시스템 Host, Header를 관리하고 요청, 응답 Spec을 관리하고 예외 처리에 대한 수준을 통일하는 모듈
    - 독립 모듈 
      - db 관련 의존성만 관리
    - 공통 모듈
        - Middleware (Type, Util 등)
        - 의존성 X (시간 범위 등을 관리)