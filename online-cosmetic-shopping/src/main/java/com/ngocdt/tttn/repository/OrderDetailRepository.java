package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "select isnull(sum(price*quantity), 0) from OrderDetail where orderID=:id")
    long sumPaymentByOrderID(@Param("id") Integer id);
}
