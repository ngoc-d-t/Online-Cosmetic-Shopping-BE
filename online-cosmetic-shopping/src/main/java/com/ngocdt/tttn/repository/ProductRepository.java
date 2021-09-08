package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Product;
import com.ngocdt.tttn.service.impl.ProductServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    List<Product> findByCategory_CategoryID(Integer categoryID);
    List<Product> findByCategory_CategoryIDAndNameLike(Integer categoryID, String name);
    List<Product> findByNameLike(String name);
    List<Product> findAllByQuantityIsGreaterThan(int quantity);
    @Query(value="select p2.productID, p2.branchOrigin, p2.description, p2.name,p2.otherName, p2.quantity, p2.volumn, p2.whereProduction, p2.categoryID, p2.image, p2.supplierID, p2.characteristicID, p2.ingredientID, p2.originID, p2.sizeID, p2.skinTypeID, p2.toneID, p2.brandID from (select productID from DiscountDetail d1 inner join (select d2.discountID from Discount d2 where d2.endTime >= cast(getdate() as date) and d2.startTime <= cast(getdate() as date) )d3 on d1.discountID = d3.discountID) dd inner join (select * from product p where p.quantity > 0) p2 on dd.productID = p2.productID",nativeQuery=true)
    List<Product> findAllByDiscount();
//
//    @Query(value = ProductServiceImpl.findByCondition_QUERY, nativeQuery = true)
//    List<Product> findByCondition(@Param("specification") String specification);
}
