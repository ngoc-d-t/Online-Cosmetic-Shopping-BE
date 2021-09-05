package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_CategoryIDAndQuantityGreaterThan(Integer categoryID, Integer quantity);
    List<Product> findByCategory_CategoryIDAndNameLikeAndQuantityGreaterThan(Integer categoryID, String name, Integer quantity);
    List<Product> findByNameLikeAndQuantityGreaterThan(String name,Integer quantity);
    List<Product> findAllByQuantityIsGreaterThan(int quantity);
    @Query(value="select p2.productID, p2.branchOrigin, p2.description, p2.name, p2.quantity, p2.volumn, p2.whereProduction, p2.categoryID, p2.image, p2.supplierID from (select productID from DiscountDetail d1 inner join Discount d2 on d1.discountID = d2.discountID where d2.endTime >= cast(getdate() as date) and d2.startTime <= cast(getdate() as date)) dd inner join (select * from product p where p.quantity > 0) p2 on dd.productID = p2.productID",nativeQuery=true)
    List<Product> findAllByDiscount();
}
