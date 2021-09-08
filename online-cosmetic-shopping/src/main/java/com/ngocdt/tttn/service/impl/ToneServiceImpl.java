package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.ToneDTO;
import com.ngocdt.tttn.repository.ToneRepository;
import com.ngocdt.tttn.service.ToneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class ToneServiceImpl implements ToneService {
    private final ToneRepository toneRepo;

    @Override
    public List<ToneDTO> showAll() {
        return toneRepo.findAll().stream().map(ToneDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public ToneDTO showOne(Integer id) {
        return null;
    }

    @Override
    public ToneDTO update(ToneDTO dto) {
        return null;
    }

    @Override
    public ToneDTO create(ToneDTO dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
