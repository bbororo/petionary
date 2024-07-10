package org.pp.petionary.global.example;

import lombok.RequiredArgsConstructor;


import org.pp.petionary.global.example.ExampleDto;
import org.pp.petionary.global.dto.CommonResponseDto;
import org.pp.petionary.global.service.CommonService;
import org.pp.petionary.global.type.SuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final CommonService commonService;

    // 반환값이 없을 때
    public CommonResponseDto<Object> examplePost() {
        return commonService.successResponse(SuccessCode.EXAMPLE_SUCCESS.getDescription(), HttpStatus.OK, null);
    }

    // 반환값이 있을 때
    public CommonResponseDto<Object> exampleGet() {


        ExampleDto exampleDto = new ExampleDto("반환 예시 입니다.");

        return commonService.successResponse(SuccessCode.EXAMPLE_SUCCESS.getDescription(), HttpStatus.OK, exampleDto);
    }
}
