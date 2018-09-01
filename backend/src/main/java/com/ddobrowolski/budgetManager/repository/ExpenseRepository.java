package com.ddobrowolski.budgetManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddobrowolski.budgetManager.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	public List<Expense> findByUserId(Long userId);
	public List<Expense> findByDateStringAndUserId(String dateString, Long userId);
	public List<Expense> findByMonthStringAndUserId(String monthString, Long userId);
}
