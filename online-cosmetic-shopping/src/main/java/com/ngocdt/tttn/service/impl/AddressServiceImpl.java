package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.AddressDTO;
import com.ngocdt.tttn.entity.Address;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.AddressRepository;
import com.ngocdt.tttn.repository.CustomerRepository;
import com.ngocdt.tttn.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepo;
    private final CustomerRepository customerRepo;
    @Override
    public List<AddressDTO> showAll() {
        return addressRepo.findAll().stream().map(AddressDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public AddressDTO showOne(Integer id) {
        return AddressDTO.toDTO(addressRepo.findById(id).orElse(null));
    }

    @Override
    public AddressDTO update(AddressDTO dto) {
        if(!addressRepo.existsById(dto.getAddressID()))
            throw new BadRequestException("Bab request.");
        Address address = AddressDTO.toEntity(dto);
        return AddressDTO.toDTO(addressRepo.save(address));
    }

    @Override
    public AddressDTO create(AddressDTO dto) {
        if(!customerRepo.existsById(dto.getCustomerID()))
            throw new BadRequestException("Customer not found.");
        Address address = AddressDTO.toEntity(dto);
        address.setAddressID(0);
        return AddressDTO.toDTO(addressRepo.save(address));
    }

    @Override
    public void delete(Integer id) {
        if(!addressRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        addressRepo.deleteById(id);
    }
}
