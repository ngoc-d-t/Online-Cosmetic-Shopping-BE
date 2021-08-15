package com.ngocdt.tttn.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Character")
@Getter
@Setter
public class Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int characterID;

	@Column
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "product_character", joinColumns = { @JoinColumn(name = "characterID") }, inverseJoinColumns = {
			@JoinColumn(name = "productID") })
	private Set<Product> product = new HashSet<>();
}
