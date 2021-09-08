package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.CharacteristicDTO;
import com.ngocdt.tttn.repository.CharacteristicRepository;
import com.ngocdt.tttn.service.CharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class CharacteristicServiceImpl implements CharacteristicService {
    private final CharacteristicRepository characteristicRepo;

    @Override
    public List<CharacteristicDTO> showAll() {
        return characteristicRepo.findAll().stream().map(CharacteristicDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public CharacteristicDTO showOne(Integer id) {
        return null;
    }

    @Override
    public CharacteristicDTO update(CharacteristicDTO dto) {
        return null;
    }

    @Override
    public CharacteristicDTO create(CharacteristicDTO dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

}
