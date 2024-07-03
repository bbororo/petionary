package org.pp.petionary.repository.item;

import org.pp.petionary.entity.item.ProductImg;
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
