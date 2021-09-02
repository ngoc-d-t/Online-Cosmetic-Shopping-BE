package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocdt.tttn.entity.ProductPrice;

import java.util.Date;

public class ProductPriceDTO {
    private int productID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Date date;
    private float price;

    public static ProductPriceDTO toDTO(ProductPrice pp) {
        if (pp == null)
            return null;
        ProductPriceDTO dto = new ProductPriceDTO();
        dto.setProductID(pp.getProductID());
        dto.setDate(pp.getDate());
        dto.setPrice(pp.getPrice());
        return dto;
    }

    public static ProductPrice toEntity(ProductPriceDTO dto) {
        if (dto == null)
            return null;
        ProductPrice pp = new ProductPrice();
        pp.setProductID(dto.getProductID());
        pp.setDate(dto.getDate());
        pp.setPrice(dto.getPrice());
        return pp;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
