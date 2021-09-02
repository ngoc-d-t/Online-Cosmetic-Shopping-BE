package com.ngocdt.tttn.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ReceiptionDetail")
@Data
@NoArgsConstructor
@IdClass(ReceiptionDetailKey.class)
public class ReceiptionDetail {
    @Id
    @Column(name = "productID",nullable = false)
    private int productID;

    @Id
    @Column(name = "receiptionID",nullable = false)
    private int receiptionID;

    @Column
    private int quantity;

    @Column
    private float price;

    @ManyToOne(fetch =FetchType.EAGER)
    @MapsId("productID")
    @JoinColumn(name = "productID")
    private Product product;

    @ManyToOne(fetch =FetchType.EAGER)
    @MapsId("receiptionID")
    @JoinColumn(name = "receiptionID")
    private Receiption receiption;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Receiption getReceiption() {
        return receiption;
    }

    public void setReceiption(Receiption receiption) {
        this.receiption = receiption;
    }
}
