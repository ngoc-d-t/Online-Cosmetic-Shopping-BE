package com.ngocdt.tttn.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Revenue")
public class ReportRevenue {
    @Id
    private Integer id;
    private String name;
    private float totalPrice;
    private int quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
