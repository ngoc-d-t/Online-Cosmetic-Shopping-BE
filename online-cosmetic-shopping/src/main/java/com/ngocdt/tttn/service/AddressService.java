package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.AddressDTO;
import com.ngocdt.tttn.entity.Customer;

import java.util.List;

public interface AddressService extends GenericService<AddressDTO, Integer> {
    List<AddressDTO> showByCustomer(Integer id);

    List<AddressDTO> show(Customer customer);
}
