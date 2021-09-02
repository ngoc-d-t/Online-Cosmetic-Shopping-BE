package com.ngocdt.tttn.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Invoice")
@Getter
@Setter
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int invoiceID;
	
	@Column
	private int taxCode;
	
	@Column
	private Date date;
	
	@OneToOne
	@JoinColumn(name="orderID",unique = true)
	private Order order;

	@ManyToOne
	@JoinColumn(name="employeeID",nullable=false)
	private Employee employee;


	public int getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}

	public int getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(int taxCode) {
		this.taxCode = taxCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
