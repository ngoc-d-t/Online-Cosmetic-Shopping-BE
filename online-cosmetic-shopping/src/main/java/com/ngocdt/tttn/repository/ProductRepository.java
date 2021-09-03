package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_CategoryIDAndQuantityGreaterThan(Integer categoryID, Integer quantity);
    List<Product> findByCategory_CategoryIDAndNameLikeAndQuantityGreaterThan(Integer categoryID, String name, Integer quantity);
    List<Product> findByNameLikeAndQuantityGreaterThan(String name,Integer quantity);
    @Query(value="select * from (select * from Product where quantity >0) a inner join (select top 6 od.productID from OrderDetail od group by od.productID order by sum(od.quantity) desc) b on a.productID = b.productID", nativeQuery=true)
    List<Product> findBestSelling();
    List<Product> findAllByQuantityIsGreaterThan(int quantity);
}
