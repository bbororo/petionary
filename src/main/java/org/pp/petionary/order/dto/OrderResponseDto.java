package org.pp.petionary.order.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderResponseDto {

    private Long orderId;
    private String orderStatus;
    private String orderAddress;
    private String delivery_request;
    private LocalDateTime orderDate;
    private int totalPrice;
    private List<OrderProductDto> orderProductList;


}
