package org.pp.petionary.repository.product;

import org.pp.petionary.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long>{

    Product findByProductIdAndDeletedAtIsNull(Long productId);

    List<Product> findByDeletedAtIsNull();



}
