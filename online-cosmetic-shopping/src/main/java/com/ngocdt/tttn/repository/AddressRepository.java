package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
