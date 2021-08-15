package com.ngocdt.tttn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="OrderDetail", uniqueConstraints = {@UniqueConstraint(columnNames = {"orderID", "productID"})})
@Getter
@Setter
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int orderDetailID;
	
	@Column(nullable=false)
	private float price;
	
	@Column
	private float discount;
	
	@Column(nullable=false)
	private int quantity;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="orderID",nullable=false)
	private Order order;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="productID",nullable=false)
	private Product product;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "orderDetail_returnItem", joinColumns = { @JoinColumn(name = "orderDetailID") }, inverseJoinColumns = {
			@JoinColumn(name = "returnItemID") })
	private Set<ReturnItem> returnItem = new HashSet<>();

	public int getOrderDetailID() {
		return orderDetailID;
	}

	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Set<ReturnItem> getReturnItem() {
		return returnItem;
	}

	public void setReturnItem(Set<ReturnItem> returnItem) {
		this.returnItem = returnItem;
	}
}
