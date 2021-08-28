package com.ngocdt.tttn.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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

	@Column
	private String image;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "category")
	private List<Product> products;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "category_character", joinColumns = { @JoinColumn(name = "categoryID") }, inverseJoinColumns = {
			@JoinColumn(name = "characterID") })
	private Set<Character> character = new HashSet<>();
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

	public String getImage() {
		return image;
	}

	public Set<Character> getCharacter() {
		return character;
	}

	public void setCharacter(Set<Character> character) {
		this.character = character;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
