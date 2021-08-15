package com.ngocdt.tttn.service;

import com.ngocdt.tttn.dto.CustomerDTO;

import javax.servlet.http.HttpServletRequest;

public interface CustomerService extends GenericService<CustomerDTO,Integer> {
    CustomerDTO update(CustomerDTO dto, HttpServletRequest request);
}
