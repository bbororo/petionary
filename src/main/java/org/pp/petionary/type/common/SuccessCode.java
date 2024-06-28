package org.pp.petionary.type.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessCode {

    EXAMPLE_SUCCESS("성공 예시 코드 입니다."),

    // 멤버
    MEMBER_FOUND("유저 정보 조회에 성공하였습니다."),
    EMAIL_AVAILABLE("사용 가능한 이메일입니다"),
    SIGNUP_SUCCESS("회원가입이 완료되었습니다."),
    MYINFO_SUCCESS("내 정보 조회에 성공하였습니다."),
    MYINFO_UPDATE_SUCCESS("내 정보 수정에 성공하였습니다.")

    ;

    private final String description;
}
