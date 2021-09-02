package com.ngocdt.tttn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReturnItemDetailKey implements Serializable {
    @Column(name = "returnItemID",nullable = false)
    private int returnItemID;
    @Column(name = "orderDetailID",nullable = false)
    private int orderDetailID;

    public int getReturnItemID() {
        return returnItemID;
    }

    public void setReturnItemID(int returnItemID) {
        this.returnItemID = returnItemID;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }
}
