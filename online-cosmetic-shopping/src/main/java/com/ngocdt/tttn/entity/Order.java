package com.ngocdt.tttn.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.ngocdt.tttn.enums.OrderState;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Entity
@Table(name="Orders")
@Getter
@Setter
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int orderID;
	
	@Column
	private Date date = new Date();

	@Enumerated(EnumType.STRING)
	@Column
	private OrderState state = OrderState.UNCONFIRMED;

	@Column
	private float totalPrice;

	@Column
	private float totalDiscount;

	@Column
	private String receiverName;

	@Column
	private String phoneNumber;

	@Column
	private String receiverAddress;

	@Column
	private float paid;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="customerID")
	private Customer customer;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="employeeID")
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="shipperID")
	private Employee shipper;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "order")
	private List<OrderDetail> orderDetails;

	public float getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(float totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OrderState getState() {
		return state;
	}

	public void setState(OrderState state) {
		this.state = state;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getShipper() {
		return shipper;
	}

	public void setShipper(Employee shipper) {
		this.shipper = shipper;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public float getPaid() {
		return paid;
	}

	public void setPaid(float paid) {
		this.paid = paid;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
}
