package com.ngocdt.tttn.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ReturnItem")
@Getter
@Setter
public class ReturnItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int returnItemID;
	
	@Column
	private Date date;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "orderDetail_returnItem", joinColumns = { @JoinColumn(name = "returnItemID") }, inverseJoinColumns = {
			@JoinColumn(name = "orderDetailID") })
	private Set<OrderDetail> orderDetail = new HashSet<>();

	@OneToOne
	@JoinColumn(name="invoiceID",unique = true)
	private Invoice invoice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeID", nullable = false)
	private Employee employee;
}
