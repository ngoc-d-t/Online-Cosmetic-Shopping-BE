package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Characteristic;

import javax.persistence.Column;

public class CharacteristicDTO {
    private int id;
    private String description;

    public static CharacteristicDTO toDTO(Characteristic character){
        if(character == null)
            return null;
        CharacteristicDTO characteristicDTO = new CharacteristicDTO();
        characteristicDTO.setId(character.getId());
        characteristicDTO.setDescription(character.getDescription());
        return characteristicDTO;
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
