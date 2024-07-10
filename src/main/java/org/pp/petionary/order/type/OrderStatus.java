package org.pp.petionary.order.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    // 취소완료, 배송중, 배송완료, 반품완료
    ORDER_COMPLETE("주문완료"),
    CANCELED("취소완료"),
    DELIVERING("배송중"),
    RECEIVING("배송완료"),
    REQUEST_REFUND("반품 신청"),
    REFUNDED("반품완료");

    private final String description;
}
