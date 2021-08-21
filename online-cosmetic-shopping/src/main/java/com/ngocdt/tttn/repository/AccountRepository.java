package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
    Optional<Account> findByAccountID(Integer id);
}
