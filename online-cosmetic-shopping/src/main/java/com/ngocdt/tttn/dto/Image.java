package com.ngocdt.tttn.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {
    private String base64String;

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }
}
