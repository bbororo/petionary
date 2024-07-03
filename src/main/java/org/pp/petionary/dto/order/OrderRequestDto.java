package org.pp.petionary.dto.order;

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
