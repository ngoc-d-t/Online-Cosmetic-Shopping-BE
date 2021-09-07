package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Origin;

import javax.persistence.Column;

public class OriginDTO {
    private int id;
    private String name;

    public static OriginDTO toDTO(Origin origin){
        if(origin == null)
            return null;
        OriginDTO dto = new OriginDTO();
        dto.setId(origin.getId());
        dto.setName(origin.getName());
        return dto;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
