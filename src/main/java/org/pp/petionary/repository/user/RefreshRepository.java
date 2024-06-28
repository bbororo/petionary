package org.pp.petionary.repository.user;

import jakarta.transaction.Transactional;
import org.pp.petionary.entity.user.RefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshRepository extends JpaRepository<RefreshEntity, Long> {

    Boolean existsByRefresh(String refresh);

    @Transactional
    void deleteByRefresh(String refresh);

}
