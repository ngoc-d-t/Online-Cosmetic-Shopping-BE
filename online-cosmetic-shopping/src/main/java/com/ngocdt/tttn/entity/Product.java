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
	private String direction;

	@Column
	private String volumn;

	@Column
	private String image;

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

	@Column(nullable=false)
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryID", nullable = false)
	private Category category;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<ProductPrice> productPrices;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_character", joinColumns = { @JoinColumn(name = "productID") }, inverseJoinColumns = {
			@JoinColumn(name = "characterID") })
	private Set<Character> character = new HashSet<>();

	@OneToMany(fetch=FetchType.LAZY,mappedBy="product")
	private List<DiscountDetail> discountDetals = new ArrayList<>();
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="product")
	private List<Comment> comments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employeeID", nullable = false)
	private Employee employee;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_receiption", joinColumns = { @JoinColumn(name = "productID") }, inverseJoinColumns = {
			@JoinColumn(name = "receiptionID") })
	private Set<Receiption> receiptions = new HashSet<>();

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

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
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

	public Set<Character> getCharacter() {
		return character;
	}

	public void setCharacter(Set<Character> character) {
		this.character = character;
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

	public Set<Receiption> getReceiptions() {
		return receiptions;
	}

	public void setReceiptions(Set<Receiption> receiptions) {
		this.receiptions = receiptions;
	}

	public List<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(List<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
