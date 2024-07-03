package org.pp.petionary.dto.order;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemDto {
    private Long orderItemId;
    private String productName;
    private int count;
    private int price;
}
