package com.ngocdt.tttn.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ProductPrice")
@Data
@NoArgsConstructor
@IdClass(ProductPriceKey.class)
public class ProductPrice {

	@Id
	@Column(name = "productID", nullable = false)
	private int productID;

	@Id
	@Column(name = "date",nullable = false)
	private Date date = new Date();

	@Column
	private float price;

	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
}
