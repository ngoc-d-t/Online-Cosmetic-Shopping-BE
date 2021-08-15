package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory_CategoryID(Integer categoryID);
}
