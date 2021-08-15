package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
}
