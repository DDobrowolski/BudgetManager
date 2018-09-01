package com.ddobrowolski.budgetManager.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Expense {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	@NotNull
	private BigDecimal sum;
	@NotNull
	private String dateString;
	@NotNull
	private String monthString;
	@ManyToOne
	private User user;
	
	public enum Category {
		FOOD, INSURANCE, TRAVEL, HOUSE, RELAX, SHOPPING, OTHERS;
	}
	
	@NotNull
	private Category category;
	public Expense() {}
	public Expense(String name, BigDecimal sum, Long userId, Category category) {
		this.name = name;
		this.sum = sum;
		this.category = category;
		this.user = new User(userId, "", "", new BigDecimal(0), "");
		
	}
}
