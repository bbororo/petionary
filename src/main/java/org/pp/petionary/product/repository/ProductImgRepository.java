package org.pp.petionary.product.repository;

import org.pp.petionary.product.entity.ProductImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductImgRepository extends JpaRepository<ProductImg, Long> {

    @Query("SELECT pi " +
            "FROM ProductImg pi " +
            "LEFT JOIN pi.product p " +
            "WHERE p.productId = :productId ")
    List<ProductImg> findProductImgByProductId(@Param("productId") Long productId);

}
