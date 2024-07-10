package org.pp.petionary.product.dto;

import lombok.Builder;
import lombok.Getter;
import org.pp.petionary.product.type.ProductStatus;

@Getter
@Builder
public class ProductDto {

    private Long productId;
    private String name;
    private String content;
    private int price;
    private int sale;
    private ProductStatus productStatus;
    private StockDto stockDto;
//    private List<ProductImg> productImgList;
    private String mainImgPath;
}
