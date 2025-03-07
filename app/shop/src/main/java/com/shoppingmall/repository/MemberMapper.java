package com.shoppingmall.repository;

import com.shoppingmall.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface MemberMapper {

    int signUp(Member member);

    void signUpWithSocialLogin(Member member);

    Optional<Member> getMemberByEmail(@Param("email") String email, @Param("registrationId") String registrationId);

    Optional<Member> getMemberById(Long memberId);

    Optional<Member> getMemberByEmailWithSocialLogin(String email, String registrationId);

    void updateMemberByEmailAndPicture(Member member);

    Optional<Member> getMemberByAccount(String username);

    int checkDuplMemberAccount(Member member);
}
