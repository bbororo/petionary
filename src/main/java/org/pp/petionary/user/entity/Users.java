package org.pp.petionary.user.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.pp.petionary.global.entity.BaseTime;
import org.pp.petionary.user.type.UserRoleEnum;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
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

    public void userModify(String address, String phone){
        this.address = address;
        this.phone = phone;
    }
}