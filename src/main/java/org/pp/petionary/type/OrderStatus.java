package org.pp.petionary.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    // 취소완료, 배송중, 배송완료, 반품완료
    CANCEL("취소완료"),
    DELIVERING("배송중"),
    RECEIVING("배송완료"),
    REFUND("반품완료");

    private final String description;
}
