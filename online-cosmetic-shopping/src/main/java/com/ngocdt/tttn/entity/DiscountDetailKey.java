package com.ngocdt.tttn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DiscountDetailKey implements Serializable {
	@Column(name = "discountID",nullable = false)
	private int discountID;
	@Column(name = "productID",nullable = false)
	private int productID;

	public int getDiscountID() {
		return discountID;
	}

	public void setDiscountID(int discountID) {
		this.discountID = discountID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}
}
