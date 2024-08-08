package org.pp.petionary.product.repository;

import jakarta.persistence.LockModeType;
import org.pp.petionary.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository <Product, Long>{

    Product findByProductIdAndDeletedAtIsNull(Long productId);

    List<Product> findByDeletedAtIsNull();

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    @Query(" SELECT p " +
            "FROM Product p " +
            "LEFT JOIN FETCH p.stock s " +
            "WHERE p.productId =:productId ")
    Optional<Product> findByProductWithPessimisticLock(@Param("productId") Long productId);

}
