package com.shoppingmall.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingmall.ShopApplication;
import com.shoppingmall.constant.Gender;
import com.shoppingmall.dto.request.MemberRequestDto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(classes = ShopApplication.class)
class MemberRestControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("회원 가입 성공")
    void testSignUp() throws Exception {
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name("김영민")
                .account("test01")
                .password("yyyyjjj*1")
                .email("test@gmail.com")
                .phoneNumber("010-2222-3333")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1993-08-23")
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(1008)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 성공 하였습니다")));
    }

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("이름이 null인 경우 테스트")
    void testMemberNameNull() throws Exception {
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name(null)
                .account("test01")
                .password("yyyyjjj*1")
                .email("test@gmail.com")
                .phoneNumber("010-2222-3333")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1993-08-23")
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.name", Matchers.containsString("이름은 필수 입력 항목 입니다")));
    }

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("이름이 한글자 미만인 경우 테스트")
    void testMemberNameLessThan1() throws Exception {
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name("김")
                .account("test01")
                .password("yyyyjjj*1")
                .email("test@gmail.com")
                .phoneNumber("010-2222-3333")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1993-08-23")
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.name", Matchers.containsString("두 글자 이상, 여섯 글자 이하의 이름을 입력해주세요")));
    }

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("이름이 여섯글자 초과인 경우 테스트")
    void testMemberNameOverThan6() throws Exception {
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name("산다라박이무니다")
                .account("test02")
                .password("1234ssss***")
                .email("test02@gmail.com")
                .phoneNumber("010-1111-4444")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.F)
                .birthDate("1994-09-11")
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.name", Matchers.containsString("두 글자 이상, 여섯 글자 이하의 이름을 입력해주세요")));
    }

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("ID가 null인 경우 테스트")
    void testMemberIdNull() throws Exception {
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name("김주영")
                .account(null)
                .password("1234ssss***")
                .email("test02@gmail.com")
                .phoneNumber("010-1111-4444")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.F)
                .birthDate("1994-09-11")
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.account", Matchers.containsString("ID는 필수 입력 항목 입니다")));
    }

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("ID가 30자 이상인 경우 테스트 - 예외 발생")
    void testMemberIdOverThan30() throws Exception {
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name("김주영")
                .account("aaaaaaaaaabbbbbbbbbbdddddddddd3")
                .password("1234ssss***")
                .email("test02@gmail.com")
                .phoneNumber("010-1111-4444")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.F)
                .birthDate("1994-09-11")
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.account", Matchers.containsString("30자 이하의 ID만 입력 가능합니다")));
    }

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("비밀번호가 null인 경우")
    void testMemberPasswordNull() throws Exception{
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name("김주영")
                .account("help123")
                .password(null)
                .email("help123@gmail.com")
                .phoneNumber("010-2222-3333")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("2000-09-11")
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.password", Matchers.containsString("비밀번호는 필수 입력 항목")));
    }

    private static Stream<Arguments> validationMemberPasswordGroup() {
        MemberRequestDto memberRequestDto1 = MemberRequestDto.builder()
                .name("김주영")
                .account("user01")
                .password("sjsjsjsjs") // 문자만 있는 경우
                .email("user01@gmail.com")
                .phoneNumber("010-1111-4444")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("2002-01-11")
                .build();

        MemberRequestDto memberRequestDto2 = MemberRequestDto.builder()
                .name("김하늘")
                .account("user02")
                .password("23213213213") // 숫자만 있는 경우
                .email("hadmdm@gmail.com")
                .phoneNumber("010-1111-4444")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1950-04-23")
                .build();

        MemberRequestDto memberRequestDto3 = MemberRequestDto.builder()
                .name("노홍철")
                .account("user03")
                .password("*&^%$#@!") // 특수문자만 있는 경우
                .email("hong@gmail.com")
                .phoneNumber("010-2222-3333")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1988-08-29")
                .build();

        MemberRequestDto memberRequestDto4 = MemberRequestDto.builder()
                .name("강호동")
                .account("user04")
                .password("1234567") // 8글자 미만인 경우
                .email("gang@gmail.com")
                .phoneNumber("010-8888-6666")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.F)
                .birthDate("1979-02-02")
                .build();

        return Stream.of(
                Arguments.of(memberRequestDto1),
                Arguments.of(memberRequestDto2),
                Arguments.of(memberRequestDto3),
                Arguments.of(memberRequestDto4)
        );
    }

    @ParameterizedTest
    @MethodSource("validationMemberPasswordGroup")
    // @WithMockUser(roles = "USER")
    @DisplayName("비밀번호 형식에 맞지 않는 경우")
    void testMemberPasswordNotMatchedPattern(MemberRequestDto memberRequestDto) throws Exception{
        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.password", Matchers.containsString("영어와 특수문자를 포함한 최소 8자 이상의 비밀번호를 입력해주세요")));
    }

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("이메일이 null인 경우 테스트")
    void testMemberEmailNull() throws Exception {
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name("김주영")
                .account("help123")
                .password("*yjyjyjyj277227")
                .email(null)
                .phoneNumber("010-2222-3333")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("2000-09-11")
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.email", Matchers.containsString("이메일은 필수 입력 항목입니다")));
    }

    private static Stream<Arguments> validationMemberEmailGroup() {
        MemberRequestDto memberRequestDto1 = MemberRequestDto.builder()
                .name("김주영")
                .account("user01")
                .password("*yjym2222345")
                .email("user01") // @ 없는 경우
                .phoneNumber("010-1111-4444")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("2002-01-11")
                .build();

        MemberRequestDto memberRequestDto2 = MemberRequestDto.builder()
                .name("김하늘")
                .account("user02")
                .password("*yjym2222345")
                .email("@gmail.com") // @부터 있는 경우
                .phoneNumber("010-1111-4444")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1950-04-23")
                .build();

        MemberRequestDto memberRequestDto3 = MemberRequestDto.builder()
                .name("노홍철")
                .account("user03")
                .password("*yjym2222345")
                .email("@") // @만 있는 경우
                .phoneNumber("010-2222-3333")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1988-08-29")
                .build();

        return Stream.of(
                Arguments.of(memberRequestDto1),
                Arguments.of(memberRequestDto2),
                Arguments.of(memberRequestDto3)
        );
    }

    @ParameterizedTest
    @MethodSource("validationMemberEmailGroup")
    // @WithMockUser(roles = "USER")
    @DisplayName("이메일 형식에 맞지 않는 경우")
    void testMemberEmailNotMatchedPattern(MemberRequestDto memberRequestDto) throws Exception{
        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.email", Matchers.containsString("올바른 메일 형식이 아닙니다")));
    }

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("휴대폰 번호가 null인 경우")
    void testMemberPhoneNumberNull() throws Exception {
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name("김주영")
                .account("help123")
                .password("*yjyjyjyj277227")
                .email("juyn@naver.com")
                .phoneNumber(null)
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1878-01-22")
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.phoneNumber", Matchers.containsString("휴대폰 번호는 필수 입력 항목입니다")));
    }

    private static Stream<Arguments> validationMemberPhoneNumberGroup() {
        MemberRequestDto memberRequestDto1 = MemberRequestDto.builder()
                .name("김주영")
                .account("user01")
                .password("*yjym2222345")
                .email("user01@naver.com")
                .phoneNumber("010-") // 중간, 마지막 번호 없는 경우
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("2002-01-11")
                .build();

        MemberRequestDto memberRequestDto2 = MemberRequestDto.builder()
                .name("김하늘")
                .account("user02")
                .password("*yjym2222345")
                .email("hanle@gmail.com")
                .phoneNumber("010-1111") // 마지막 번호 없는 경우
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1950-04-23")
                .build();

        MemberRequestDto memberRequestDto3 = MemberRequestDto.builder()
                .name("노홍철")
                .account("user03")
                .password("*yjym2222345")
                .email("junmin@gmail.com")
                .phoneNumber("010-2222-333") // 마지막 번호가 세자리 인 경우
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate("1988-08-29")
                .build();

        return Stream.of(
                Arguments.of(memberRequestDto1),
                Arguments.of(memberRequestDto2),
                Arguments.of(memberRequestDto3)
        );
    }

    @ParameterizedTest
    @MethodSource("validationMemberPhoneNumberGroup")
    // @WithMockUser(roles = "USER")
    @DisplayName("휴대폰 형식에 맞지 않는 경우")
    void testMemberPhoneNotMatchedPattern(MemberRequestDto memberRequestDto) throws Exception{
        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.phoneNumber", Matchers.containsString("올바른 휴대폰번호 형식이 아닙니다")));
    }

    @Test
    // @WithMockUser(roles = "USER")
    @DisplayName("생년월일이 null인 경우")
    void testMemberBirthDate() throws Exception {
        //given
        MemberRequestDto memberRequestDto = MemberRequestDto.builder()
                .name("김주영")
                .account("help123")
                .password("*yjyjyjyj277227")
                .email("juyn@naver.com")
                .phoneNumber("010-2222-3333")
                .certYn("Y")
                .accountCertYn("Y")
                .gender(Gender.M)
                .birthDate(null)
                .build();

        //when
        ResultActions result  = mockMvc.perform(
                post("/api/v1/member/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(memberRequestDto))
        );

        //then
        result.andExpect(status().isBadRequest())
                .andDo(print())
                .andExpect(jsonPath("$.code", equalTo(2006)))
                .andExpect(jsonPath("$.message", Matchers.containsString("회원 가입에 실패하였습니다")))
                .andExpect(jsonPath("$.errorMessage.birthDate", Matchers.containsString("생년월일은 필수 입력 항목입니다")));
    }
}
