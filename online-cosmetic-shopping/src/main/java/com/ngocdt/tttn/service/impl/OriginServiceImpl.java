package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.OriginDTO;
import com.ngocdt.tttn.repository.OriginRepository;
import com.ngocdt.tttn.service.OriginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class OriginServiceImpl implements OriginService {
    private final OriginRepository originRepo;
    @Override
    public List<OriginDTO> showAll() {
        return originRepo.findAll().stream().map(OriginDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public OriginDTO showOne(Integer id) {
        return null;
    }

    @Override
    public OriginDTO update(OriginDTO dto) {
        return null;
    }

    @Override
    public OriginDTO create(OriginDTO dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
