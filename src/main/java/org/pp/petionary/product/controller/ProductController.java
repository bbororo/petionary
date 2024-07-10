package org.pp.petionary.product.controller;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.global.dto.CommonResponseDto;
import org.pp.petionary.global.dto.ResultDto;
import org.pp.petionary.product.dto.ProductDto;
import org.pp.petionary.product.dto.ProductListDto;
import org.pp.petionary.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/petionary")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/{productId}")
    public ResponseEntity<ResultDto<ProductDto>> getProduct(@PathVariable Long productId){
        CommonResponseDto<Object> commonResponseDto = productService.getProduct(productId);
        ResultDto<ProductDto> resultDto = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());
        resultDto.setData((ProductDto) commonResponseDto.getData());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(resultDto);
    }

    @GetMapping("/product")
    public ResponseEntity<ResultDto<ProductListDto>> getProductList() {

        CommonResponseDto<Object> commonResponseDto = productService.getProductList();
        ResultDto<ProductListDto> resultDto = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());
        resultDto.setData((ProductListDto) commonResponseDto.getData());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(resultDto);

    }
}
