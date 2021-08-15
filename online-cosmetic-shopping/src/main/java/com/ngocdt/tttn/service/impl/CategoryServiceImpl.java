package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.CategoryDTO;
import com.ngocdt.tttn.entity.Category;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.CategoryRepository;
import com.ngocdt.tttn.service.CategoryService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepo;

    @Override
    public List<CategoryDTO> showAll() {
        return categoryRepo.findAll().stream().map(CategoryDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO showOne(Integer id) {
        return CategoryDTO.toDTO(categoryRepo.findById(id).orElse(null));
    }

    @Override
    public CategoryDTO update(CategoryDTO dto) {
        if(!categoryRepo.existsById(dto.getCategoryID()))
            throw new BadRequestException("Category not found");
        Category cate=CategoryDTO.toEntity(dto);
        return CategoryDTO.toDTO(categoryRepo.save(cate));
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category cate = CategoryDTO.toEntity(dto);
        cate.setCategoryID(0);
        return CategoryDTO.toDTO(categoryRepo.save(cate));
    }

    @Override
    public void delete(Integer id) {
        if(!categoryRepo.existsById(id))
            throw new BadRequestException("Category-not-found");
        categoryRepo.deleteById(id);
    }
}
