package org.pp.petionary.entity.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pp.petionary.entity.basetime.BaseTime;
import org.pp.petionary.type.UserRoleEnum;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class Users extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;


    @Builder
    public Users(String username, String password, String email, String address, String phone, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }
}