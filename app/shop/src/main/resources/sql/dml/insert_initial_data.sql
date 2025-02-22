-- 1정규화 : 하나의 행에 하나의 데이터만 삽입
-- 2정규화 : 현재 테이블의 주제와 관련없는 컬럼을 다른 테이블로 뺴는 작업
USE SHOPPINGMALL;

-- INSERT INTO MEMBER
INSERT INTO MEMBER (name, account, password, email, phone_number, picture, birth_date, use_yn, cert_yn, role)
VALUES
    ('아이유', 'user1', 'password1', 'user1@example.com', '010-1111-1111', NULL, '1993-09-23', 'Y', 'Y', 'USER'),
    ('장동건', 'user2', 'password2', 'user2@example.com', '010-2222-2222', NULL, '1994-05-15', 'Y', 'N', 'USER'),
    ('원빈', 'user3', 'password3', 'user3@example.com', '010-3333-3333', NULL, '1995-12-07', 'Y', 'Y', 'USER'),
    ('현빈', 'user4', 'password4', 'user4@example.com', '010-4444-4444', NULL, '1996-03-29', 'Y', 'N', 'USER'),
    ('김주혁', 'user5', 'password5', 'user5@example.com', '010-5555-5555', NULL, '1997-08-11', 'Y', 'Y', 'USER'),
    ('김종국', 'user6', 'password6', 'user6@example.com', '010-6666-6666', NULL, '1998-01-03', 'Y', 'N', 'USER'),
    ('아이린', 'user7', 'password7', 'user7@example.com', '010-7777-7777', NULL, '1999-11-21', 'Y', 'Y', 'USER'),
    ('김태희', 'user8', 'password8', 'user8@example.com', '010-8888-8888', NULL, '2000-06-14', 'Y', 'N', 'USER'),
    ('송혜교', 'user9', 'password9', 'user9@example.com', '010-9999-9999', NULL, '2001-09-05', 'Y', 'Y', 'USER'),
    ('한가인', 'user10', 'password10', 'user10@example.com', '010-1010-1010', NULL, '2002-02-27', 'Y', 'N', 'USER');

INSERT INTO POST_CATEGORY (category_id, category_name)
VALUES
    (1, '일상/소통'),
    (2, '반려/생활'),
    (3, '운동/헬스'),
    (4, '연애/사랑'),
    (5, '상담/문의'),
    (6, '사주/운세'),
    (7, '기술/토론'),
    (8, '여행/만남'),
    (9, '직장/회사'),
    (10, '외국인/만남'),
    (999, '기타');

-- INSERT INTO POST
INSERT INTO POST (title, content, member_id, category_id, read_cnt, fixed_yn, del_yn, create_date, update_date)
VALUES
    ('제목1', '내용1', 5, 6, 0, 'N', 'N', '2023-05-27 22:45:54', '2023-06-01 23:18:16'),
    ('제목2', '내용2', 2, 2, 2, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목3', '내용3', 5, 8, 8, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목4', '내용4', 5, 6, 6, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목5', '내용5', 8, 4, 4, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목6', '내용6', 6, 2, 2, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목7', '내용7', 6, 10, 10, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목8', '내용8', 2, 4, 4, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목9', '내용9', 8, 5, 5, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목10', '내용10', 2, 2, 9, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목11', '내용11', 10, 10, 10, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목12', '내용12', 10, 4, 4, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목13', '내용13', 3, 9, 9, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목14', '내용14', 1, 7, 7, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목15', '내용15', 5, 6, 6, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목16', '내용16', 9, 10, 10, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목17', '내용17', 2, 6, 6, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목18', '내용18', 6, 6, 1, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목19', '내용19', 4, 5, 7, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목20', '내용20', 1, 1, 2, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목21', '내용21', 5, 4, 4, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목22', '내용22', 3, 3, 3, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목23', '내용23', 10, 10, 3, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목24', '내용24', 9, 9, 1, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목25', '내용25', 7, 7, 9, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목26', '내용26', 2, 8, 8, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목27', '내용27', 6, 6, 10, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목28', '내용28', 9, 3, 3, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목29', '내용29', 7, 1, 1, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목30', '내용30', 3, 1, 1, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목31', '내용31', 5, 10, 10, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목32', '내용32', 4, 7, 6, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목33', '내용33', 1, 10, 10, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목34', '내용34', 5, 10, 10, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목35', '내용35', 7, 10, 10, 'N', 'N', '2023-05-27 22:45:54', '2023-05-27 22:45:54'),
    ('제목36', '내용36', 9, 5, 5, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목37', '내용37', 9, 3, 3, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목38', '내용38', 4, 8, 8, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목39', '내용39', 1, 10, 10, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목40', '내용40', 9, 10, 10, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목41', '내용41', 6, 6, 6, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목42', '내용42', 8, 5, 5, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목43', '내용43', 3, 8, 8, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목44', '내용44', 5, 4, 4, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목45', '내용45', 7, 4, 4, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목46', '내용46', 4, 2, 2, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목47', '내용47', 1, 7, 7, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목48', '내용48', 5, 7, 7, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목49', '내용49', 10, 3, 3, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55'),
    ('제목50', '내용50', 5, 7, 7, 'N', 'N', '2023-05-27 22:45:55', '2023-05-27 22:45:55');

# 계층형 대댓글 데이터 생성
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (NULL, 20, '첫 번째 댓글', 1, 'N', NOW(), NOW());
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (1, 20, '첫 번째 댓글의 첫 번째 대댓글', 2, 'N', NOW(), NOW());
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (1, 20, '첫 번째 댓글의 두 번째 대댓글', 3, 'N', NOW(), NOW());
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (2, 20, '첫 번째 댓글의 첫 번째 댓글의 대댓글', 3, 'N', NOW(), NOW());
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (NULL, 20, '두 번째 댓글', 4, 'N', NOW(), NOW());
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (4, 20, '두 번째 댓글의 첫 번째 대댓글', 6, 'N', NOW(), NOW());
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (4, 20, '두 번째 댓글의 두 번째 대댓글', 7, 'N', NOW(), NOW());
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (NULL, 20, '세 번째 댓글', 5, 'N', NOW(), NOW());
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (7, 20, '세 번째 댓글의 첫 번째 대댓글', 8, 'N', NOW(), NOW());
INSERT INTO COMMENT (parent_id, post_id, content, member_id, del_yn, create_date, update_date) VALUES (7, 20, '세 번째 댓글의 두 번째 대댓글', 9, 'N', NOW(), NOW());