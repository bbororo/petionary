package org.pp.petionary.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordUpdateDto {
    private String currentPassword;
    private String newPassword;
}
