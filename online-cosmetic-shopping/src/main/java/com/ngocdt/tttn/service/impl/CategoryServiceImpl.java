package com.ngocdt.tttn.service.impl;

import com.ngocdt.tttn.dto.CategoryDTO;
import com.ngocdt.tttn.entity.Category;
import com.ngocdt.tttn.exception.BadRequestException;
import com.ngocdt.tttn.repository.CategoryRepository;
import com.ngocdt.tttn.service.CategoryService;
import com.ngocdt.tttn.utils.DriveAPIUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    @Value("${upload.path}")
    private String fileUpload;

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
        if (!categoryRepo.existsById(dto.getCategoryID()))
            throw new BadRequestException("Category not found");
        Category cate = CategoryDTO.toEntity(dto);

        Category c = categoryRepo.findById(dto.getCategoryID()).get();
        if (!dto.getImage().equals(c.getImage())) {
            cate.setImage(uploadImage(dto.getImage()));
        } else cate.setImage(dto.getImage());
        return CategoryDTO.toDTO(categoryRepo.save(cate));
    }

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        Category cate = CategoryDTO.toEntity(dto);
        cate.setCategoryID(0);
        cate.setImage(uploadImage(dto.getImage()));
        return CategoryDTO.toDTO(categoryRepo.save(cate));
    }

    @Override
    public void delete(Integer id) {
        if (!categoryRepo.existsById(id))
            throw new BadRequestException("Category-not-found");
        categoryRepo.deleteById(id);
    }

    private String uploadImage(String imagePath) {
        String fileName = UUID.randomUUID().toString();
        try {
            FileCopyUtils.copy(Base64Utils.decodeFromString(imagePath.split(",")[1]),
                    new File(this.fileUpload + fileName));
            return DriveAPIUtils.upload(new File(fileUpload + fileName));
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            throw new BadRequestException("image-wrong");
        }
    }
}
