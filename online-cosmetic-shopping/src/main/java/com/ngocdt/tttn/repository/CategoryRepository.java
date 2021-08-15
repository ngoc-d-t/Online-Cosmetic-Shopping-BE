package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
