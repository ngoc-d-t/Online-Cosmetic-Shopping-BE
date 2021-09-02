package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.ReceiptionDetail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;

@Getter
@Setter
public class ReceiptionDetailDTO {
    private int productID;
    private int receiptionID;
    private int quantity;
    private float price;

    public static ReceiptionDetailDTO toDTO(ReceiptionDetail receiptionDetail){
        if(receiptionDetail == null)
            return null;
        ReceiptionDetailDTO dto = new ReceiptionDetailDTO();
        dto.setProductID(receiptionDetail.getProductID());
        dto.setReceiptionID(receiptionDetail.getReceiptionID());
        dto.setQuantity(receiptionDetail.getQuantity());
        dto.setPrice(receiptionDetail.getPrice());
        return dto;
    }
    public static ReceiptionDetail toEntity(ReceiptionDetailDTO dto){
        if(dto == null)
            return null;
        ReceiptionDetail receiptionDetail = new ReceiptionDetail();
        receiptionDetail.setProductID(dto.getProductID());
        receiptionDetail.setReceiptionID(dto.getReceiptionID());
        receiptionDetail.setQuantity(dto.getQuantity());
        receiptionDetail.setPrice(dto.getPrice());
        return receiptionDetail;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getReceiptionID() {
        return receiptionID;
    }

    public void setReceiptionID(int receiptionID) {
        this.receiptionID = receiptionID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
