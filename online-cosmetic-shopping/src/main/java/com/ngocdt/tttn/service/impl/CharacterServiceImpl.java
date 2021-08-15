package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.CharacterDTO;
import com.ngocdt.tttn.entity.Character;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.CharacterRepository;
import com.ngocdt.tttn.service.CharacterService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Data
public class CharacterServiceImpl implements CharacterService {
    private final CharacterRepository characterRepo;
    @Override
    public List<CharacterDTO> showAll() {
        return characterRepo.findAll().stream().map(CharacterDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public CharacterDTO showOne(Integer id) {
        return CharacterDTO.toDTO(characterRepo.findById(id).orElse(null));
    }

    @Override
    public CharacterDTO update(CharacterDTO dto) {
        if(!characterRepo.existsById(dto.getCharacterID()))
            throw new BadRequestException("Bad request.");
        Character chara = CharacterDTO.toEntity(dto);
        return CharacterDTO.toDTO(characterRepo.save(chara));
    }

    @Override
    public CharacterDTO create(CharacterDTO dto) {
        Character chara = CharacterDTO.toEntity(dto);
        chara.setCharacterID(0);
        return CharacterDTO.toDTO(characterRepo.save(chara));
    }

    @Override
    public void delete(Integer id) {
        if(!characterRepo.existsById(id))
            throw new BadRequestException("Bad request.");
        characterRepo.deleteById(id);
    }
}
