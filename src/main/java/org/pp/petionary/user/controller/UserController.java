package org.pp.petionary.user.controller;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.user.dto.CustomUserDetails;
import org.pp.petionary.user.dto.PasswordUpdateDto;
import org.pp.petionary.user.dto.SignupRequestDto;
import org.pp.petionary.global.dto.CommonResponseDto;
import org.pp.petionary.global.dto.ResultDto;
import org.pp.petionary.user.dto.UserModifyDto;
import org.pp.petionary.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    // 유저 정보 수정
    @PutMapping("/modify")
    public ResponseEntity<ResultDto<Void>> userModify(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                            @RequestBody UserModifyDto userModifyDto) {
        CommonResponseDto<Object> commonResponseDto = userService.modifyUser(customUserDetails, userModifyDto);
        ResultDto<Void> result = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(result);

    }

    @PutMapping("/password")
    public ResponseEntity<ResultDto<Void>> updatePassword(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                          @RequestBody PasswordUpdateDto passwordUpdateDto) {
        CommonResponseDto<Object> commonResponseDto = userService.modifyPassword(customUserDetails, passwordUpdateDto);
        ResultDto<Void> result = ResultDto.in(commonResponseDto.getStatus(), commonResponseDto.getMessage());

        return ResponseEntity.status(commonResponseDto.getHttpStatus()).body(result);
    }
}
