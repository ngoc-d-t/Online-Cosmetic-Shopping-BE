package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.CustomerDTO;
import com.ngocdt.tttn.dto.RegisterDTO;
import com.ngocdt.tttn.entity.Account;
import com.ngocdt.tttn.entity.Customer;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.AccountRepository;
import com.ngocdt.tttn.repository.CustomerRepository;
import com.ngocdt.tttn.service.CustomerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Data
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepo;
    private final AccountRepository accountRepo;

    @Override
    public List<CustomerDTO> showAll() {
        return customerRepo.findAll().stream().map(CustomerDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO showOne(Integer id) {
        return CustomerDTO.toDTO(customerRepo.findById(id).orElse(null));
    }

    @Override
    public CustomerDTO update(CustomerDTO dto) {
//        if (!customerRepo.existsById(dto.getCustomerID()))
//            throw new BadRequestException("Bad request.");
//        Customer customer = CustomerDTO.toEntity(dto);
//        return CustomerDTO.toDTO(customerRepo.save(customer));
        return null;
    }

    @Override
    public CustomerDTO update(CustomerDTO dto, HttpServletRequest request) {
        Account accountCurrent = accountRepo.findByEmail(request.getAttribute("email").toString()).get();
        Customer customer = CustomerDTO.toEntity(dto);
        customer.setCustomerID(accountCurrent.getCustomer().getCustomerID());
        return CustomerDTO.toDTO(customerRepo.save(customer));
    }

    @Override
    public CustomerDTO create(CustomerDTO dto) {
        Customer customer = CustomerDTO.toEntity(dto);
        customer.setCustomerID(0);
        return CustomerDTO.toDTO(customerRepo.save(customer));
    }

    @Override
    public void delete(Integer id) {
        if (!customerRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        customerRepo.deleteById(id);
    }
}
