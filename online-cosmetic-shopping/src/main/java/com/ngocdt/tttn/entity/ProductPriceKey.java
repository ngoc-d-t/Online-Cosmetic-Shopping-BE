package com.ngocdt.tttn.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductPriceKey implements Serializable {

	@Column(name = "productID", nullable = false)
	private int productID;
	@Column(name = "date", nullable = false)
	private Date date = new Date();

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
}
