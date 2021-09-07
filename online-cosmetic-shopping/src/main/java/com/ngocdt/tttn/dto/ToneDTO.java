package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Tone;

import javax.persistence.Column;

public class ToneDTO {
    private int id;
    private String description;

    public static ToneDTO toDTO(Tone tone) {
        if (tone == null)
            return null;
        ToneDTO dto = new ToneDTO();
        dto.setId(tone.getId());
        dto.setDescription(tone.getDescription());
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
