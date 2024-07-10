package org.pp.petionary.product.service;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.global.dto.CommonResponseDto;
import org.pp.petionary.product.dto.ProductDto;
import org.pp.petionary.product.dto.ProductListDto;
import org.pp.petionary.product.dto.StockDto;
import org.pp.petionary.product.entity.Product;
import org.pp.petionary.product.repository.ProductImgRepository;
import org.pp.petionary.product.repository.ProductRepository;
import org.pp.petionary.global.service.CommonService;
import org.pp.petionary.global.type.SuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImgRepository productImgRepository;
    private final CommonService commonService;

    public CommonResponseDto<Object> getProduct(Long productId) {

        Product product = productRepository.findByProductIdAndDeletedAtIsNull(productId);

        StockDto stockDto = StockDto.builder()
                .sellAmount(product.getStock().getSellAmount())
                .stockAmount(product.getStock().getStockAmount())
                .build();

        String mainImgPath = product.getProductImgList().get(0).getImgUrl();

//        List<ProductImg> productImgList = productImgRepository.findProductImgByProductId(productId);

        ProductDto productDto = ProductDto.builder()
                .productId(productId)
                .name(product.getName())
                .content(product.getContent())
                .price(product.getPrice())
                .sale(product.getSale())
                .productStatus(product.getProductStatus())
                .stockDto(stockDto)
                .mainImgPath(mainImgPath)
                .build();

        return commonService.successResponse(SuccessCode.PRODUCT_INQUIRY_SUCCESS.getDescription(), HttpStatus.OK, productDto);
    }

    public CommonResponseDto<Object> getProductList() {

        List<Product> products = productRepository.findByDeletedAtIsNull();
        List<ProductDto> productDtoList = new ArrayList<>();

        for(Product product : products ){
            String mainImgPath = product.getProductImgList().get(0).getImgUrl();

            StockDto stockDto = StockDto.builder()
                    .sellAmount(product.getStock().getSellAmount())
                    .stockAmount(product.getStock().getStockAmount())
                    .build();

            ProductDto productDto = ProductDto.builder()
                    .productId(product.getProductId())
                    .name(product.getName())
                    .content(product.getContent())
                    .price(product.getPrice())
                    .sale(product.getSale())
                    .productStatus(product.getProductStatus())
                    .mainImgPath(mainImgPath)
                    .stockDto(stockDto)
                    .build();

            productDtoList.add(productDto);
        }

        ProductListDto productListDto = ProductListDto.builder()
                .productListDto(productDtoList)
                .build();

        return commonService.successResponse(SuccessCode.PRODUCT_LIST_INQUIRY_SUCCESS.getDescription(), HttpStatus.OK, productListDto);
    }
}
