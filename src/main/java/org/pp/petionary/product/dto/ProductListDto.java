package org.pp.petionary.product.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductListDto {

    private List<ProductDto> productListDto;
//    private PaginationDto paginationDto;


    @Builder
    public ProductListDto(List<ProductDto> productListDto) {
        this.productListDto = productListDto;
    }
}
