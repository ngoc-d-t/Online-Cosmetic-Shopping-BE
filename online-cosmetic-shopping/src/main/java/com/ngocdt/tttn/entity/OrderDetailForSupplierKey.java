package com.ngocdt.tttn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderDetailForSupplierKey implements Serializable {
    @Column(name = "orderForSupplierID",nullable = false)
    private int orderForSupplierID;
    @Column(name = "productID",nullable = false)
    private int productID;

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
