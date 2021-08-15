package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.OrderDetailForSupplierKey;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailForSupplierKeyDTO {
    private int orderForSupplierID;
    private int productID;

    public static OrderDetailForSupplierKey toEntity(OrderDetailForSupplierKeyDTO dto) {
        if (dto == null)
            return null;
        OrderDetailForSupplierKey od = new OrderDetailForSupplierKey();
        od.setOrderForSupplierID(dto.getOrderForSupplierID());
        od.setProductID(dto.getProductID());
        return od;
    }

    public int getOrderForSupplierID() {
        return orderForSupplierID;
    }

    public void setOrderForSupplierID(int orderForSupplierID) {
        this.orderForSupplierID = orderForSupplierID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
