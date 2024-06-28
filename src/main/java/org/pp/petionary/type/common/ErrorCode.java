package org.pp.petionary.type.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 멤버
    USER_NOT_FOUND("유저를 찾을 수 없습니다."),
    // Member
    MEMBER_NOT_FOUND("유저 정보 조회에 실패하였습니다."),
    EMAIL_NOT_AVAILABLE("사용할 수 없는 이메일입니다."),
    NICKNAME_NOT_AVAILABLE("이미 사용 중인 닉네임입니다."),
    PHONENUM_NOT_AVAILABLE("이미 사용 중인 전화번호입니다."),
    PASSWORD_NOT_MATCH_WITH_PATTERN("비밀번호 형식과 맞지 않는 비밀번호 입니다."),
    SIGNUP_FAIL("회원가입에 실패하였습니다."),

    LOGIN_FAIL("아이디 혹은 비밀번호가 일치하지 않습니다."),
    LOGOUT_FAIL("로그아웃에 실패하였습니다."),

    MYINFO_FAIL("내 정보 조회에 실패하였습니다."),
    MYINFO_UPDATE_FAIL("내 정보 수정에 실패하였습니다."),
    PASSWORD_CHECK_FAIL("비밀번호가 일치하지 않습니다."),
    PASSWORD_UPDATE_FAIL("비밀번호 수정에 실패하였습니다."),
    PROFILE_UPDATE_FAIL("프로필 업로드에 실패하였습니다."),

    UNAUTHORIZED_ACCESS("접근 권한이 없습니다.");


    private final String description;
}
