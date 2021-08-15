package com.ngocdt.tttn.entity;


import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Comment")
@Data
@NoArgsConstructor
@IdClass(CommentKey.class)
public class Comment {

	@Id
	@Column(name = "productID",nullable = false)
	private int productID;

	@Id
	@Column(name = "customerID",nullable = false)
	private int customerID;
	
	@Column(name="contents")
	private String contents;

	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;

	@ManyToOne
	@MapsId("customerID")
	@JoinColumn(name = "customerID")
	private Customer customer;
}
