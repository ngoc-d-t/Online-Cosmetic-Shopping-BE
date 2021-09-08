package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
}
