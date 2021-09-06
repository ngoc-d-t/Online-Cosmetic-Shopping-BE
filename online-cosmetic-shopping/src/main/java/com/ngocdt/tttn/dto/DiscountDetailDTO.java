package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.Discount;
import com.ngocdt.tttn.entity.DiscountDetail;
import com.ngocdt.tttn.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Getter
@Setter
public class DiscountDetailDTO {
    private int productID;
    private int discountID;
    private int discountPercent;
    private ProductDTO product;

    public static DiscountDetailDTO toDTO(DiscountDetail dd){
        if(dd == null)
            return null;
        DiscountDetailDTO dto = new DiscountDetailDTO();
        dto.setProductID(dd.getProductID());
        dto.setDiscountID(dd.getDiscountID());
        dto.setDiscountPercent(dd.getDiscountPercent());
        dto.setProduct(ProductDTO.toDTO(dd.getProduct()));
        return dto;
    }
    public static DiscountDetail toEntity(DiscountDetailDTO dto){
        if( dto == null)
            return null;
        DiscountDetail dd = new DiscountDetail();
        dd.setProductID(dto.getProductID());
        dd.setDiscountID(dto.getDiscountID());
        dd.setDiscountPercent(dto.getDiscountPercent());
        return dd;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }
}
