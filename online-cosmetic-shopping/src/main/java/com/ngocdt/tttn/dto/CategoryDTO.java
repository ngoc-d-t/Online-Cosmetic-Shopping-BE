package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Category;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.bridge.IMessage;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;

@Getter
@Setter
public class CategoryDTO {
    private int categoryID;
    @NotBlank(message="can not be empty.")
    private String name;

    public static CategoryDTO toDTO(Category cate){
        if(cate==null){
            return null;
        }
        CategoryDTO dto=new CategoryDTO();
        dto.setCategoryID(cate.getCategoryID());
        dto.setName(cate.getName());
        return dto;
    }

    public static Category toEntity(CategoryDTO dto){
        if(dto==null){
            return null;
        }
        Category cate=new Category();
        cate.setCategoryID(dto.getCategoryID());
        cate.setName(dto.getName());
        return cate;
    }

}
