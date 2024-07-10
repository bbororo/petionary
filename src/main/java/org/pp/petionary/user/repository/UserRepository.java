package org.pp.petionary.user.repository;

import org.pp.petionary.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Optional<Users> findByEmail(String email);


}
