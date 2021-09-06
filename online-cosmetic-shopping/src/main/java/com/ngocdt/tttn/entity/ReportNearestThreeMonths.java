package com.ngocdt.tttn.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="NearestThreeMonths")
public class ReportNearestThreeMonths {
    @Id
    private String state;
    private int quantity;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
