package org.pp.petionary.product.repository;

import org.pp.petionary.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long>{

    Product findByProductIdAndDeletedAtIsNull(Long productId);

    List<Product> findByDeletedAtIsNull();



}
