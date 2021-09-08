package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.SkinTypeDTO;
import com.ngocdt.tttn.repository.SkinTypeRepository;
import com.ngocdt.tttn.service.SkinTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class SkinTypeServiceImpl implements SkinTypeService {
    private final SkinTypeRepository skinTypeRepo;
    @Override
    public List<SkinTypeDTO> showAll() {
        return skinTypeRepo.findAll().stream().map(SkinTypeDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public SkinTypeDTO showOne(Integer id) {
        return null;
    }

    @Override
    public SkinTypeDTO update(SkinTypeDTO dto) {
        return null;
    }

    @Override
    public SkinTypeDTO create(SkinTypeDTO dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
