package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.DiscountDetailKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
public class DiscountDetailKeyDTO {
    private int discountID;
    private int productID;
    public static DiscountDetailKey toEntity(DiscountDetailKeyDTO dto){
        if(dto == null)
            return null;
        DiscountDetailKey key = new DiscountDetailKey();
        key.setDiscountID(dto.getDiscountID());
        key.setProductID(dto.getProductID());
        return key;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
