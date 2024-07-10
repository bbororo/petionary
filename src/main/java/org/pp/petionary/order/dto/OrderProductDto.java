package org.pp.petionary.order.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderProductDto {
    private Long orderProductId;
    private String productName;
    private int count;
    private int price;
}
