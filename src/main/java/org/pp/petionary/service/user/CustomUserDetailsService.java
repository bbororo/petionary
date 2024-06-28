package org.pp.petionary.service.user;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.dto.user.CustomUserDetails;
import org.pp.petionary.entity.user.Users;
import org.pp.petionary.exception.NotFoundException;
import org.pp.petionary.repository.user.UserRepository;
import org.pp.petionary.type.common.ErrorCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Users users = userRepository.findByEmail(email)
//                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
//
//        return new CustomUserDetails(users);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //DB에서 조회
        Users userData = userRepository.findByUsername(username);

        if (userData != null) {

            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userData);
        }

        return null;
    }
}