package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    List<Discount> findAllByDiscountIDNotIn(List<Integer> id);
}
