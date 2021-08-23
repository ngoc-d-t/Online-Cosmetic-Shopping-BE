package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.ProductPrice;
import com.ngocdt.tttn.entity.ProductPriceKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, ProductPriceKey> {
    List<ProductPrice> findByProduct_ProductIDAndAndDateIsLessThanEqual(Integer id, Date date);
}
