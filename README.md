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
| Docker          | 23.0.5 | x    |
| AWS EC2             |       | x    |

## ERD

## 🎢 구현 기능

- [Software Requirements Specification](https://docs.google.com/spreadsheets/d/1Wppy4RiBwldQL77RcXoLq9OkdGnVFCzIv5TQO75fZl8/edit?usp=sharing) 

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

## API Docs

> ✏️ [swagger](http://localhost:8080/swagger-ui/index.html)
