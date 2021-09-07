package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Ingredient;

import javax.persistence.Column;

public class IngredientDTO {
    private int id;
    private String description;

    public static IngredientDTO toDTO(Ingredient ingredient){
        if(ingredient == null)
            return null;
        IngredientDTO dto = new IngredientDTO();
        dto.setId(ingredient.getId());
        dto.setDescription(ingredient.getDescription());
        return dto;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
