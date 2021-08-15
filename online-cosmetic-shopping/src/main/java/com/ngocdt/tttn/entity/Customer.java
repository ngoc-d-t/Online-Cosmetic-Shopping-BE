package com.ngocdt.tttn.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Customer")
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int customerID;

	@Column
	private String fullname;

	@Column
	private Date birthday;

	@Column
	private int sex;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<Address> addresses;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	private List<Order> orders;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="customer")
	private List<Comment> comments;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
