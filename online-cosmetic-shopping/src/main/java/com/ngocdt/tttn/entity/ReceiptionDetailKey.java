package com.ngocdt.tttn.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ReceiptionDetailKey implements Serializable {
    @Column(name = "productID", nullable = false)
    private int productID;
    @Column(name = "receiptionID", nullable = false)
    private int receiptionID;
}
