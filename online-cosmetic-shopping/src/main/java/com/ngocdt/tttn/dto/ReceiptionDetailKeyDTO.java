package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.ReceiptionDetailKey;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptionDetailKeyDTO {
    private int productID;
    private int receiptionID;

    public static ReceiptionDetailKey toEntity(ReceiptionDetailKeyDTO dto) {
        if (dto == null)
            return null;
        ReceiptionDetailKey key = new ReceiptionDetailKey();
        key.setProductID(dto.getProductID());
        key.setReceiptionID(dto.getReceiptionID());
        return key;
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
}
