package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
