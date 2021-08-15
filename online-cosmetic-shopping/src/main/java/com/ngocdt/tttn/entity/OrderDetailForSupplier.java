package com.ngocdt.tttn.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "OrderDetailForSupplier")
@IdClass(OrderDetailForSupplierKey.class)
@Data
@NoArgsConstructor
public class OrderDetailForSupplier {
    @Id
    @Column(name = "orderForSupplierID",nullable = false)
    private int orderForSupplierID;

    @Id
    @Column(name = "productID", nullable = false)
    private int productID;

    @Column
    private int quantity;

    @Column
    private float price;

    @ManyToOne(fetch =FetchType.EAGER)
    @MapsId("productID")
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne(fetch =FetchType.EAGER)
    @MapsId("orderForSupplierID")
    @JoinColumn(name = "orderForSupplierID")
    private OrderForSupplier orderForSupplier;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderForSupplier getOrderForSupplier() {
        return orderForSupplier;
    }

    public void setOrderForSupplier(OrderForSupplier orderForSupplier) {
        this.orderForSupplier = orderForSupplier;
    }
}
