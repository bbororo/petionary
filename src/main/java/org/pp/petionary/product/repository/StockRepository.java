package org.pp.petionary.product.repository;

import org.pp.petionary.product.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long > {
}
