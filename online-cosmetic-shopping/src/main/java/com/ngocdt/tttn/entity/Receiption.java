package com.ngocdt.tttn.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Receiption")
@Getter
@Setter
public class Receiption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int receiptionID;

	@OneToOne
	@JoinColumn(name="orderForSupplierID",unique = true)
	private OrderForSupplier orderForSupplier;

	@Column
	private Date date=new Date();

	@Column
	private long totalPrice;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeID", nullable = false)
	private Employee employee;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receiption")
	private List<ReceiptionDetail> receiptionDetails;

	public int getReceiptionID() {
		return receiptionID;
	}

	public void setReceiptionID(int receiptionID) {
		this.receiptionID = receiptionID;
	}

	public OrderForSupplier getOrderForSupplier() {
		return orderForSupplier;
	}

	public void setOrderForSupplier(OrderForSupplier orderForSupplier) {
		this.orderForSupplier = orderForSupplier;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<ReceiptionDetail> getReceiptionDetails() {
		return receiptionDetails;
	}

	public void setReceiptionDetails(List<ReceiptionDetail> receiptionDetails) {
		this.receiptionDetails = receiptionDetails;
	}
}
