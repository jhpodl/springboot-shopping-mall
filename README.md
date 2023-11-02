# 🛒 스프링 부트로 만드는 쇼핑몰 프로젝트

## ⛏ 사용 기술

### 📌 Backend
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

## ✅ ERD

## 🎢 구현 기능

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

## 🚀 프로젝트 실행 방법

### 🐳 docker-compose up

> MySQL PORT 3306과 충돌이 발생하는 경우 아래 링크를 참고 해주세요  
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

### 🐳 docker-compose down

```shell
# 프로젝트 루트 경로 이동
cd .
```

```shell
# docker 서버 중지
./stop_docker.sh
```

## 📜 API Docs

> ✏️ [swagger Document](http://localhost:8080/swagger-ui/index.html)

- API 규격서의 경우 swagger를 기반으로 작성 하였습니다