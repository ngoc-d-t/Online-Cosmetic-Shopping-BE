package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.ProductPrice;
import com.ngocdt.tttn.entity.ProductPriceKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, ProductPriceKey> {
    ProductPrice findTop1ByProduct_ProductIDAndDateLessThanEqual(Integer id, Date date);
}
