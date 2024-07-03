package org.pp.petionary.dto.product;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockDto {

    private int stockAmount;
    private int sellAmount;

}
