package org.pp.petionary.controller.user;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.dto.ExampleDto;
import org.pp.petionary.dto.user.CustomUserDetails;
import org.pp.petionary.dto.user.PasswordUpdateDto;
import org.pp.petionary.dto.user.SignupRequestDto;
import org.pp.petionary.dto.common.CommonResponseDto;
import org.pp.petionary.dto.common.ResultDto;
import org.pp.petionary.dto.user.UserModifyDto;
import org.pp.petionary.service.user.UserService;
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
