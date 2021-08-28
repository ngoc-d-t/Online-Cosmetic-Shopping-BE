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
	@JoinTable(name = "category_character", joinColumns = { @JoinColumn(name = "characterID") }, inverseJoinColumns = {
			@JoinColumn(name = "categoryID") })
	private Set<Category> categories = new HashSet<>();

	public int getCharacterID() {
		return characterID;
	}

	public void setCharacterID(int characterID) {
		this.characterID = characterID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
}
