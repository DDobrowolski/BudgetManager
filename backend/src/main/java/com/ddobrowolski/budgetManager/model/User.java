package com.ddobrowolski.budgetManager.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
	
	@Id
	private Long id;
	private String email;
	private String password;
	private BigDecimal monthBudget;
	public User() {}
	public User(Long id, String email, String password, BigDecimal monthBudget) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.monthBudget = monthBudget;
	}
	
	
}
