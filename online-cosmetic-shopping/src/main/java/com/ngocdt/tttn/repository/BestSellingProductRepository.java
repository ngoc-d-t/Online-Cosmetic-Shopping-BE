package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.BestSellingProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BestSellingProductRepository extends JpaRepository<BestSellingProduct, Integer> {
    @Query(value="select * from (select * from Product p1 where p1.quantity > 0 ) p inner join (select od.productID, sum(od.quantity) sale_per_month from OrderDetail od inner join (select o1.orderID from Orders o1 where o1.date >= (GETDATE() - 30) and o1.state = 'DELIVERED') o on od.orderID = o.orderID group by od.productID) rs on rs.productID = p.productID order by rs.sale_per_month desc", nativeQuery=true)
    List<BestSellingProduct> findBestSelling();
}
