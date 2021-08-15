package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.OrderDetailForSupplier;
import com.ngocdt.tttn.entity.OrderDetailForSupplierKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailForSupplierRepository extends JpaRepository<OrderDetailForSupplier, OrderDetailForSupplierKey> {
    @Query(value = "select isnull(sum(price*quantity), 0) from OrderDetailForSupplier where orderForSupplierID=:id")
    long sumPaymentByOrderForSupplierID(@Param("id") Integer id);
}
