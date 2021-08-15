package com.ngocdt.tttn.dto;

import com.ngocdt.tttn.entity.OrderDetailForSupplier;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Optional;

@Getter
@Setter
public class OrderDetailForSupplierDTO {
    private int orderForSupplierID;
    private int productID;
    private int quantity;
    private float price;

    public static OrderDetailForSupplierDTO toDTO(OrderDetailForSupplier od){
        if(od == null)
            return null;
        OrderDetailForSupplierDTO dto = new OrderDetailForSupplierDTO();
        dto.setOrderForSupplierID(od.getOrderForSupplierID());
        dto.setProductID(od.getProductID());
        dto.setQuantity(od.getQuantity());
        dto.setPrice(od.getPrice());

        return dto;
    }
    public static OrderDetailForSupplier toEntity(OrderDetailForSupplierDTO dto){
        if(dto == null)
            return null;
        OrderDetailForSupplier od = new OrderDetailForSupplier();
        od.setOrderForSupplierID(dto.getOrderForSupplierID());
        od.setProductID(dto.getProductID());
        od.setQuantity(dto.getQuantity());
        od.setPrice(dto.getPrice());
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
