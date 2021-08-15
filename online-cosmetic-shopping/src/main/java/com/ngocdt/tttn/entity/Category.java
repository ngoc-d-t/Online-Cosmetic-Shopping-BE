package com.ngocdt.tttn.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Category")
@Getter
@Setter
public class Category {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryID;
	
	@Column(nullable=false)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "category")
	private List<Product> products;

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
