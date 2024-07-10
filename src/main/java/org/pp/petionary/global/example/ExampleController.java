package org.pp.petionary.global.example;


import lombok.RequiredArgsConstructor;
import org.pp.petionary.global.dto.CommonResponseDto;
import org.pp.petionary.global.dto.ResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExampleController {

    private final ExampleService exampleService;

    // 반환값이 없을 때
    @PostMapping("/example")
    public ResponseEntity<ResultDto<Void>> examplePost(){
        CommonResponseDto<Object> commonResponseDto = exampleService.examplePost();
        ResultDto<Void> resultDto = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(resultDto);
    }

    // 반환값이 존재하는 예시 코드 입니다.
    @GetMapping("/example")
    public ResponseEntity<ResultDto<ExampleDto>> exampleGet() {
        CommonResponseDto<Object> commonResponseDto = exampleService.exampleGet();
        ResultDto<ExampleDto> result = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());
        result.setData((ExampleDto) commonResponseDto.getData());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(result);

    }
}