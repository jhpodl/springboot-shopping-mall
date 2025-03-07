# 🛒 스프링 부트로 만드는 쇼핑몰 프로젝트

## ⛏ 01. 사용 기술

### 📌 01-1. Backend
| 기술              | 버전    | 적용 여부 |
|-----------------|-------|------|
| Java            | 11    | o    |
| Spring Boot     | 2.7.1 | o    |
| Spring Security | 2.7.2 | o    |
| Spring Data JPA | 2.7.2 | o    |
| MyBatis         | 3.0.1 | o    |
| MySQL           | 8.0.28 | o    |
| Swagger         | 3.0.0 | o    |
| Docker          | 23.0.5 | o    |
| AWS EC2             |       | x    |

## ✅ 02. ERD

## 🎢 03. 구현 기능

> ✏️ [요구사항 정의서](https://docs.google.com/spreadsheets/d/1Wppy4RiBwldQL77RcXoLq9OkdGnVFCzIv5TQO75fZl8/edit?usp=sharing) 

- 공통
  - 회원가입
    - 회원가입
    - 아이디 중복 체크
    - 이메일 인증
  - 로그인
    - SNS 로그인
    - 로그인
  - 회원 정보 관리
    - 아이디 찾기
    - 비밀번호 초기화
- 관리자
  - 관리자 상품 관리
    - 전체 상품 조회
    - 상품 등록
    - 상품 수정
    - 상품 재고 변경
    - 상품 상태 변경
    - 상품 페이징 처리
  - 관리자 주문 관리
    - 전체 주문 목록 조회
    - 배송 상태 변경
    - 주문 목록 페이징 처리
- 사용자
  - 사용자 게시판
    - 전체 게시글 조회
    - 상세 게시글 조회
    - 게시글 검색
    - 게시글 작성
    - 게시글 삭제
    - 게시글 수정
    - 다중 파일 업로드
    - 다중 파일 다운로드
    - 다중 파일 압축 다운로드
    - 댓글 작성
    - 댓글 수정
    - 댓글 삭제
- 마이페이지
  - 프로필 사진 등록
  - 프로필 수정
  - 상품 게시글 조회
- 상품
  - 상품 메인 화면 전체 상품 목록 조회
  - 상품 상세 화면 상세 상품 조회
- 상품 주문
  - 상품 주문

## 📜 04. API Docs

> ✏️ [swagger Document](http://localhost:8080/swagger-ui/index.html)

- API 규격서의 경우 swagger를 기반으로 작성 하였습니다

## 🚀 05. 프로젝트 실행 방법

> 프로젝트 구동 전 Docker 환경과 상관없이 반드시 아래 내용을 설정 해주셔야 합니다.  
> MySQL 데이터의 경우 docker-compose를 구동하는 경우 docker container 내 자동 생성합니다.

### 05-1. Google 이메일 전송 관련 SMTP 설정

> 구글 이메일 전송의 경우 본인의 앱 키를 넣어주셔야 합니다  
> [Google App 비밀번호 생성](https://cloudtechflow.com/2023/10/28/%ea%b5%ac%ea%b8%80-%ec%95%b1-%eb%b9%84%eb%b0%80%eb%b2%88%ed%98%b8-%ec%83%9d%ec%84%b1%ed%95%98%ea%b8%b0/)

```yaml
# application-dev.yaml | application-prod.yaml 
  mail:
    host: smtp.gmail.com
    port: 587
    username: '<본인 이메일 입력>'
    password: '<본인 Google 앱 비밀번호 입력>'
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
            required: true
          connectiontimeout: 5000
          timeout: 5000
          writetimeout: 5000
    auth-code-expiration-millis: 1800000
```

### 05-2. SNS 소셜 로그인 관련 설정

> 소셜 로그인을 실행하기 위해서 각 플랫폼별로 제공해주는 클라이언트 ID, Secret을 기재 해주세요  
> [Spring Security + OAuth2.0으로 소셜 로그인 구현 - 구글, 네이버, 카카오](https://datamoney.tistory.com/333)

```yaml
# application-oauth-sample.yaml -> application-oauth.yaml 이름 변경 해주세요 
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: 클라이언트 ID
            client-secret: 클라이언트 Secret
            scope:
              - profile
              - email
          naver:
            client-id: 클라이언트 ID
            client-secret: 클라이언트 Secret
            redirect-uri: "{baseUrl}/{action}/oauth2/code/{registrationId}"
            authorization-grant-type: authorization_code
            scope:
              - name
              - email
              - profile_image
            client-name: Naver
          kakao:
            client-id: 클라이언트 ID
            client-secret: 클라이언트 Secret
            client-name: Kakao
            scope:
              - account_email
              - profile_nickname
              - profile_image
            authorization-grant-type: authorization_code
            redirect-uri: http://localhost:8080/login/oauth2/code/kakao
            client-authentication-method: POST
        provider:
          naver:
            authorization-uri: https://nid.naver.com/oauth2.0/authorize
            token-uri: https://nid.naver.com/oauth2.0/token
            user-info-uri: https://openapi.naver.com/v1/nid/me
            user-name-attribute: response
          kakao:
            authorization-uri: https://kauth.kakao.com/oauth/authorize
            token-uri: https://kauth.kakao.com/oauth/token
            user-info-uri: https://kapi.kakao.com/v2/user/me
            user-name-attribute: id
```

### ⚙️ 05-3. 파일 권한 변경

> 위 설정이 완료 되었으면 로트 경로의 해당 파일의 권한 변경, 실행이 가능하도록 아래 명령어 입력

```shell
# 파일 실행 가능 권한 변경
chmod +x run_docker.sh
chmod +x stop_docker.sh
```

### 🐳 05-4. docker-compose up

> MySQL PORT 3306과 충돌이 발생하는 경우 아래 링크를 참고 해주세요  
> 기존 로컬 MySQL의 구동 없이 실행이 가능하여야 합니다  
> [[Docker] docker mysql 포트 충돌 에러 (feat. 3306)](https://lealea.tistory.com/232)

```shell
# 프로젝트 clean & Jar 생성
gradle > app > shop > clean
gradle > app > shop > bootJar
```

```shell
# docker 서버 시작
# run_docker.sh 실행 시 Docker desktop의 모든 이미지를 지우고 이미지 생성
# 유의하여 사용할 필요가 존재하며, 기본적인 유효성 검증은 작성하였습니다 
./run_docker.sh
```

### 🐳 05-5. docker-compose down

```shell
# docker 서버 중지
./stop_docker.sh
```

### 05-6. 참고 사항

```shell
추가적으로 파일 업로드의 경우 본인 운영체제 맞춰서 업로드 하나  
디렉토리 이름은 본인 컴퓨터 환경에 맞춰서 작성해주시면 됩니다(application-dev.yaml)
```