package com.ngocdt.tttn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ngocdt.tttn.entity.ProductPriceKey;

import java.util.Date;

public class ProductPriceKeyDTO {
    private int productID;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date date;

    public static ProductPriceKeyDTO toDTO(ProductPriceKey pp) {
        if (pp == null)
            return null;
        ProductPriceKeyDTO dto = new ProductPriceKeyDTO();
        dto.setProductID(pp.getProductID());
        dto.setDate(pp.getDate());
        return dto;
    }

    public static ProductPriceKey toEnTiTy(ProductPriceKeyDTO dto) {
        if (dto == null)
            return null;
        ProductPriceKey pp = new ProductPriceKey();
        pp.setProductID(dto.getProductID());
        pp.setDate(pp.getDate());
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
}
