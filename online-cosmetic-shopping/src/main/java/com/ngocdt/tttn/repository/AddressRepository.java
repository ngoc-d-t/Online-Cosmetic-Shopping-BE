package com.ngocdt.tttn.repository;

import com.ngocdt.tttn.dto.AddressDTO;
import com.ngocdt.tttn.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    List<Address> findByCustomer_CustomerID(Integer customerID);
}
