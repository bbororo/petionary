package org.pp.petionary.service;

import lombok.RequiredArgsConstructor;


import org.pp.petionary.dto.ExampleDto;
import org.pp.petionary.dto.common.CommonResponseDto;
import org.pp.petionary.service.common.CommonService;
import org.pp.petionary.type.common.SuccessCode;
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
