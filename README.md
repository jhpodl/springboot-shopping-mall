# Shop

---

간단한 `쇼핑몰 제작`을 위한 웹 애플리케이션 프로젝트 입니다.
기본적으로 `Spring Boot`와 `Mybatis, JPA`를 활용하여 시스템을 구축합니다.

## 📁 목차

- 사용 기술
- ERD 설계
- 구현 기능
- API 명세서

## ⛏ 사용 기술

### 📌 Backend
| 기술              | 버전    | 사용 여부 |
|-----------------|-------|------|
| Java            | 11    | o    |
| Spring Boot     | 2.7.1 | o    |
| Spring Security | 2.7.2 | o    |
| Spring Data JPA | 2.7.2 | o    |
| MyBatis         | 3.0.1 | o    |
| MySQL           | 8.0.28 | o    |
| Swagger         | 3.0.0 | o    |
| Docker          | 23.0.5 | x    |
| AWS             |       | x    |

## ERD 설계

- `TODO`

## 🎢 구현 기능

> [Google Spreadsheet : 요구사항 정의서](https://docs.google.com/spreadsheets/d/1Wppy4RiBwldQL77RcXoLq9OkdGnVFCzIv5TQO75fZl8/edit?usp=sharing) 

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

## API 명세서

> ✏️ API 명세서의 경우 spring boot server 구동 후 아래 URL로 접속하시면 됩니다.  
> - http://localhost:8080/swagger-ui/index.html

## API 명세서

### ✅ 회원(Member)

| Method | URL                            | Description |
|--------|--------------------------------|-------------|
| GET    | /api/v1/member/exists/{account} | 아이디 중복 체크   |
| POST   | /api/v1/member/join            | 회원 가입       |

### ✅ 이메일 인증 처리(Email)

| Method | URL                             | Description |
|-------|------------------------------|--------------|
| POST  | /api/v1/email/verify-request | 이메일 인증 번호 요청 |
| GET   | /api/v1/email/verify         | 이메일 인증       |

### ✅ 게시판(Post)

| Method | URL                            | Description |
|--------|--------------------------------|------------|
| GET    | /api/v1/post                   | 게시물 목록 조회  |
| POST   | /api/v1/post                   | 게시물 저장     |
| GET    | /api/v1/post/{id}              | 게시물 상세 조회  |
| PUT    | /api/v1/post/{id}              | 게시물 수정     |
| DELETE | /api/v1/post/{id}              | 게시물 삭제     |

### ✅ 파일 처리(File)

| Method | URL                             | Description |
|-------|---------------------------------|-------------|
| GET   | /api/v1/download/{id}           | 파일 다운로드     |
| GET   | /api/v1/download/compress/{id}  | 압축 파일 다운로드  |

### ✅ 댓글 처리(Comment)

| Method | URL                            | Description |
|--------|--------------------------------|------------|
| POST   | /api/v1/post/{postId}/comments | 댓글 저장      |
| PUT    | /api/v1/post/comments          | 댓글 수정      |
| DELETE | /api/v1/post/comments          | 댓글/대댓글 삭제  |
