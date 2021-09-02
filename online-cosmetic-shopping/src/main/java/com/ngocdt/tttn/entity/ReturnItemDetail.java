package com.ngocdt.tttn.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="ReturnItemDetail")
@Getter
@Setter
@IdClass(ReturnItemDetailKey.class)
public class ReturnItemDetail {
    @Id
    @Column(name = "returnItemID",nullable = false)
    private int returnItemID;

    @Id
    @Column(name = "orderDetailID", nullable = false)
    private int orderDetailID;

    @ManyToOne(fetch =FetchType.EAGER)
    @MapsId("returnItemID")
    @JoinColumn(name = "returnItemID")
    private ReturnItem returnItem;

    @ManyToOne(fetch =FetchType.EAGER)
    @MapsId("orderDetailID")
    @JoinColumn(name = "orderDetailID")
    private OrderDetail orderDetail;

    @Column
    private int quantity;

    @Column
    private float price;

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

    public ReturnItem getReturnItem() {
        return returnItem;
    }

    public void setReturnItem(ReturnItem returnItem) {
        this.returnItem = returnItem;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
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
