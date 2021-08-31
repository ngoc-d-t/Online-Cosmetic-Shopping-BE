package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "select isnull(sum(price*quantity), 0) from OrderDetail where orderID=:id")
    float sumPaymentByOrderID(@Param("id") Integer id);
    List<OrderDetail> findAllByOrder_OrderID(Integer id);
}
