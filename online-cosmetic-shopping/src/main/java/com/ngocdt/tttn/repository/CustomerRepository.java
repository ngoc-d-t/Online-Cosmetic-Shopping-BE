package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
