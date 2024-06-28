package org.pp.petionary.service.user;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.dto.SignupRequestDto;
import org.pp.petionary.dto.common.CommonResponseDto;
import org.pp.petionary.entity.user.Users;
import org.pp.petionary.repository.user.UserRepository;
import org.pp.petionary.service.common.CommonService;
import org.pp.petionary.type.UserRoleEnum;
import org.pp.petionary.type.common.SuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CommonService commonService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    private final JwtUtil jwtUtil;


    @Transactional
    public CommonResponseDto<Object> signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = bCryptPasswordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Users isExist = userRepository.findByUsername(username);
        if (isExist != null) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<Users> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
//        UserRoleEnum role = UserRoleEnum.USER;
//        if (requestDto.isAdmin()) {
//            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
//                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
//            }
//            role = UserRoleEnum.ADMIN;
//        }

        // 사용자 등록
        Users users = Users.builder()
                .email(requestDto.getEmail())
                .password(password)
                .username(requestDto.getUsername())
                .address(requestDto.getAddress())
                .phone(requestDto.getPhone())
                .role(UserRoleEnum.USER)
                .build();
        userRepository.save(users);

        return commonService.successResponse(SuccessCode.SIGNUP_SUCCESS.getDescription(), HttpStatus.CREATED, requestDto);
    }

}