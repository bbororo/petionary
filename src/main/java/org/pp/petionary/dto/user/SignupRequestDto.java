package org.pp.petionary.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;

//    private boolean admin = false;
//    private String adminToken = "";
}
