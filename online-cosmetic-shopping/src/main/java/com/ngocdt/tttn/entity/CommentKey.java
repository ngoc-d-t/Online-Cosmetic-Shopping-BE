package com.ngocdt.tttn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CommentKey implements Serializable {
	@Column(name = "productID", nullable = false)
	private int productID;
	@Column(name = "customerID",nullable = false)
	private int customerID;

}
