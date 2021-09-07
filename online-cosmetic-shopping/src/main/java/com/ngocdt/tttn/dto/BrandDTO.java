package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Brand;

public class BrandDTO {
    private int id;
    private String name;

    public static BrandDTO toDTO(Brand brand){
        if(brand == null)
            return null;
        BrandDTO dto = new BrandDTO();
        dto.setId(brand.getId());
        dto.setName(brand.getName());
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
