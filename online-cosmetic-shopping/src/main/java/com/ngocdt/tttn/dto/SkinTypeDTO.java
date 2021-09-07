package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.SkinType;

public class SkinTypeDTO {
    private int id;
    private String type;

    public static SkinTypeDTO toDTO(SkinType type){
        if (type == null)
            return null;
        SkinTypeDTO dto = new SkinTypeDTO();
        dto.setId(type.getId());
        dto.setType(type.getType());
        return dto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
