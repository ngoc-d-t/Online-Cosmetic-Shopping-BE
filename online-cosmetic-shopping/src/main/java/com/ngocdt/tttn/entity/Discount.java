package com.ngocdt.tttn.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Discount")
@Getter
@Setter
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int discountID;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Date startTime;

	@Column(nullable = false)
	private Date endTime;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "discount")
	private List<DiscountDetail> discountDetails;

	public int getDiscountID() {
		return discountID;
	}

	public void setDiscountID(int discountID) {
		this.discountID = discountID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<DiscountDetail> getDiscountDetails() {
		return discountDetails;
	}

	public void setDiscountDetails(List<DiscountDetail> discountDetails) {
		this.discountDetails = discountDetails;
	}

}
