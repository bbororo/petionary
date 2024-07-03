package org.pp.petionary.controller.user;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.dto.user.SignupRequestDto;
import org.pp.petionary.dto.common.CommonResponseDto;
import org.pp.petionary.dto.common.ResultDto;
import org.pp.petionary.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/petionary/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResultDto<Void>>signUp(@RequestBody SignupRequestDto requestDto) {

        CommonResponseDto<Object> commonResponseDto = userService.signup(requestDto);
        ResultDto<Void> resultDto = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());
        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(resultDto);
    }

}
