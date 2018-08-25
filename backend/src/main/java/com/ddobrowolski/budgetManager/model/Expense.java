package com.ddobrowolski.budgetManager.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Expense {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	private BigDecimal sum;
	private String dateString;
	@ManyToOne
	private User user;
	
	public enum Category {
		FOOD, INSURANCE, TRAVEL, HOUSE, RELAX, SHOPPING, OTHERS;
	}
	
	private Category category;
	public Expense() {}
	public Expense(Long id, String name, BigDecimal sum, Long userId, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.sum = sum;
		this.category = category;
		this.user = new User(userId, "", "", new BigDecimal(0), "");
		
	}
}
