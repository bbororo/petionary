package org.pp.petionary.order.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderRequestDto {

    private Long productId;
    private String address;
    private String deliveryRequest;
    private int count;

}
