package org.pp.petionary.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderResponseListDto {

    private List<OrderResponseDto> orderResponseListDto;
//    private PaginationDto paginationDto;


    @Builder
    public OrderResponseListDto(List<OrderResponseDto> orderResponseListDto) {
        this.orderResponseListDto = orderResponseListDto;
    }
}
