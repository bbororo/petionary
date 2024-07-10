package org.pp.petionary.user.service;

import lombok.RequiredArgsConstructor;
import org.pp.petionary.user.dto.CustomUserDetails;
import org.pp.petionary.user.entity.Users;
import org.pp.petionary.global.exception.NotFoundException;
import org.pp.petionary.user.repository.UserRepository;
import org.pp.petionary.global.type.ErrorCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        return new CustomUserDetails(users);
    }

}