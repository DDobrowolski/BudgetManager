package com.ddobrowolski.budgetManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddobrowolski.budgetManager.model.Expense;
import com.ddobrowolski.budgetManager.repository.ExpenseRepository;

@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public List<Expense> getAllExpenses(Long userId){
		List<Expense> expenses = new ArrayList<>();
		expenseRepository.findByUserId(userId)
		.forEach(expenses::add);
		return expenses;
	}
	
	public Expense getExpense(Long id) {
		return expenseRepository.findById(id).orElse(null);
	}
	
	public void addExpense(Expense expense) {
		expenseRepository.save(expense);
	}
	
	public void updateExpense(Expense expense) {
		expenseRepository.save(expense);
	}
	
	public void deleteExpense(Long id) {
		expenseRepository.deleteById(id);
	}
}
