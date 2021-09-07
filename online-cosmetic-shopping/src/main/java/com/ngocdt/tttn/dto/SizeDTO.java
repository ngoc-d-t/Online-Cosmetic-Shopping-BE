package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Size;

import javax.persistence.Column;

public class SizeDTO {
    private int id;
    private String size;

    public static SizeDTO toDTO(Size size){
        if(size == null)
            return null;
        SizeDTO dto = new SizeDTO();
        dto.setId(size.getId());
        dto.setSize(size.getSize());
        return dto;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
