package com.ddobrowolski.budgetManager.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Expense {
	
	@Id
	private Long id;
	private String name;
	private BigDecimal sum;
	@ManyToOne
	private User user;
	public Expense() {}
	public Expense(Long id, String name, BigDecimal sum, Long userId) {
		super();
		this.id = id;
		this.name = name;
		this.sum = sum;
		this.user = new User(userId, "", "", new BigDecimal(0));
	}
	
	
}
