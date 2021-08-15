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
	private long totalPayment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeID", nullable = false)
	private Employee employee;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "receiption")
	private List<ReceiptionDetail> receiptionDetails;
}
