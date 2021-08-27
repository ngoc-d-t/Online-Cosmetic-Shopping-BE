package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.ProductPrice;
import com.ngocdt.tttn.entity.ProductPriceKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, ProductPriceKey> {
    List<ProductPrice> findByProduct_ProductIDAndAndDateIsLessThanEqual(Integer id, Date date);
    List<ProductPrice> findByProduct_ProductID(Integer id);
    @Query(value = "select top 1 * from ProductPrice pp where pp.productID = :id and pp.date <= CAST(GETDATE() as date) order by pp.date desc", nativeQuery = true)
    ProductPrice findByProduct(@Param("id") Integer id);
}
