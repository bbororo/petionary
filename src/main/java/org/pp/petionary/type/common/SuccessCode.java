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
    MYINFO_UPDATE_SUCCESS("내 정보 수정에 성공하였습니다."),

    // 상품
    PRODUCT_INQUIRY_SUCCESS("상품 조회에 성공하였습니다."),
    PRODUCT_LIST_INQUIRY_SUCCESS("상품 리스트 조회에 성공하였습니다."),

    // 주문
    ORDER_SUCCESS("상품 주문에 성공하였습니다."),
    ORDER_STATUS_INQUIRY_SUCCESS("주문 조회에 성공했습니다."),
    ORDER_CANCEL_SUCCESS("주문 취소 성공하였습니다."),
    ORDER_REFUND_REQUEST("주문 반품신청에 성공하였습니다."),
    ORDER_REFUND_SUCCESS("주문 반품에 성공하였습니다.")

    ;

    private final String description;
}
