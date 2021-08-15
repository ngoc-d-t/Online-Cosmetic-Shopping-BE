package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.SupplierDTO;
import com.ngocdt.tttn.entity.Supplier;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.SupplierRepository;
import com.ngocdt.tttn.service.SupplierService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepo;

    @Override
    public List<SupplierDTO> showAll() {
        return supplierRepo.findAll().stream().map(SupplierDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public SupplierDTO showOne(Integer id) {
        return SupplierDTO.toDTO(supplierRepo.findById(id).orElse(null));
    }

    @Override
    public SupplierDTO update(SupplierDTO dto) {
        if (!supplierRepo.existsById(dto.getSupplierID()))
            throw new BadRequestException("Bad request.");
        Supplier sup = SupplierDTO.toEntity(dto);
        return SupplierDTO.toDTO(supplierRepo.save(sup));
    }

    @Override
    public SupplierDTO create(SupplierDTO dto) {
        Supplier sup = SupplierDTO.toEntity(dto);
        sup.setSupplierID(0);
        return SupplierDTO.toDTO(supplierRepo.save(sup));
    }

    @Override
    public void delete(Integer id) {
        if (!supplierRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        supplierRepo.deleteById(id);
    }
}
