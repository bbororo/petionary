package org.pp.petionary.repository.item;

import org.pp.petionary.entity.item.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository <Product, Long>{

    Product findByProductIdAndDeletedAtIsNull(Long productId);

    List<Product> findByDeletedAtIsNull();



}
