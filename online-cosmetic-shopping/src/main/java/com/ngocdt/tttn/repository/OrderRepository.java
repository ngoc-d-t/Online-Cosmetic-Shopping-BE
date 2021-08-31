package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByCustomer_CustomerID(Integer id);
}
