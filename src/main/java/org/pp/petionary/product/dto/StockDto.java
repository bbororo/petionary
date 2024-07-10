package org.pp.petionary.product.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockDto {

    private int stockAmount;
    private int sellAmount;

}
