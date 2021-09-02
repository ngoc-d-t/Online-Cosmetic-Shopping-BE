package com.ngocdt.tttn.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Product")
@Data
@Getter
@Setter
public class Product {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productID;

	@Column(nullable = false)
	private String name;

	@Column
	private String branchOrigin;

	@Column
	private String whereProduction;
	
	@Column
	private int quantity;

	@Column
	private String volumn;

	@Column
	private String image;

	@Column(nullable=false)
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "supplierID", nullable = false)
	private Supplier supplier;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryID", nullable = false)
	private Category category;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<ProductPrice> productPrices;


	@OneToMany(fetch=FetchType.LAZY,mappedBy="product")
	private List<DiscountDetail> discountDetals = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="product")
	private List<Comment> comments = new ArrayList<>();


	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranchOrigin() {
		return branchOrigin;
	}

	public void setBranchOrigin(String branchOrigin) {
		this.branchOrigin = branchOrigin;
	}

	public String getWhereProduction() {
		return whereProduction;
	}

	public void setWhereProduction(String whereProduction) {
		this.whereProduction = whereProduction;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<DiscountDetail> getDiscountDetals() {
		return discountDetals;
	}

	public void setDiscountDetals(List<DiscountDetail> discountDetals) {
		this.discountDetals = discountDetals;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVolumn() {
		return volumn;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
