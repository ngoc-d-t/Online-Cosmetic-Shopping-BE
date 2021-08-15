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
}
