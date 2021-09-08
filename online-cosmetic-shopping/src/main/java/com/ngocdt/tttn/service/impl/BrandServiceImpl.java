package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.AddressDTO;
import com.ngocdt.tttn.dto.BrandDTO;
import com.ngocdt.tttn.repository.BrandRepository;
import com.ngocdt.tttn.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepo;
    @Override
    public List<BrandDTO> showAll() {
        return brandRepo.findAll().stream().map(BrandDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public BrandDTO showOne(Integer id) {
        return null;
    }

    @Override
    public BrandDTO update(BrandDTO dto) {
        return null;
    }

    @Override
    public BrandDTO create(BrandDTO dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
