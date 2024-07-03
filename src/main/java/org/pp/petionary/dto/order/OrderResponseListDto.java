package org.pp.petionary.dto.order;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.pp.petionary.dto.common.PaginationDto;

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
