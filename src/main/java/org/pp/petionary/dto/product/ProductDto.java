package org.pp.petionary.dto.product;

import lombok.Builder;
import lombok.Getter;
import org.pp.petionary.entity.item.ProductImg;
import org.pp.petionary.type.ProductStatus;

import java.util.List;

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
