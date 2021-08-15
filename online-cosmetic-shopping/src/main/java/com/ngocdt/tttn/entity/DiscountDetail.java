package com.ngocdt.tttn.entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "DiscountDetail")
@Data
@IdClass(DiscountDetailKey.class)
public class DiscountDetail {

	@Id
	@Column(name = "productID", nullable = false)
	private int productID;

	@Id
	@Column(name = "discountID", nullable = false)
	private int discountID;
	
	@ManyToOne
	@MapsId("discountID")
	@JoinColumn(name = "discountID")
	private Discount discount;

	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;
	
	@Column
	private float discountPercent;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getDiscountID() {
		return discountID;
	}

	public void setDiscountID(int discountID) {
		this.discountID = discountID;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}
}
