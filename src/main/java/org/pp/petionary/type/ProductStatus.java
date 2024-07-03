package org.pp.petionary.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {

    // 품절, 판매중
    SOLDOUT("품절"),
    SELLING("판매중");

    private final String description;
}
