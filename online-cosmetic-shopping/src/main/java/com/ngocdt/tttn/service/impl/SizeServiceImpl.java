package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.SizeDTO;
import com.ngocdt.tttn.repository.SizeRepository;
import com.ngocdt.tttn.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepo;
    @Override
    public List<SizeDTO> showAll() {
        return sizeRepo.findAll().stream().map(SizeDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public SizeDTO showOne(Integer id) {
        return null;
    }

    @Override
    public SizeDTO update(SizeDTO dto) {
        return null;
    }

    @Override
    public SizeDTO create(SizeDTO dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
