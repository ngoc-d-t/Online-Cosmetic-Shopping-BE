package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.IngredientDTO;
import com.ngocdt.tttn.repository.IngredientRepository;
import com.ngocdt.tttn.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepo;
    @Override
    public List<IngredientDTO> showAll() {
        return ingredientRepo.findAll().stream().map(IngredientDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public IngredientDTO showOne(Integer id) {
        return null;
    }

    @Override
    public IngredientDTO update(IngredientDTO dto) {
        return null;
    }

    @Override
    public IngredientDTO create(IngredientDTO dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
