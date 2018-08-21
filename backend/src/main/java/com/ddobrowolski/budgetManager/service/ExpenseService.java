package com.ddobrowolski.budgetManager.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Calendar now = Calendar.getInstance();
		String dateString = dateFormat.format(now.getTime()).toString();
		expense.setDateString(dateString);
		if(expense.getSum().intValue()<0) {
			System.out.println("Sum must be greater or equals to zero.");
			return;
		}
		expenseRepository.save(expense);
	}
	
	public void updateExpense(Expense expense) {
		if(expense.getSum().intValue()<0) {
			System.out.println("Sum must be greater or equals to zero.");
			return;
		}
		expenseRepository.save(expense);
	}
	
	public void deleteExpense(Long id) {
		expenseRepository.deleteById(id);
	}
	
	public List<Expense> findByDateString(String dateString) {
		return expenseRepository.findByDateString(dateString);
	}
	
	public Map <String, BigDecimal> getCategorySum (Long id) {
		Map <String, BigDecimal> categorySum = new HashMap<>();
		categorySum.put("FOOD", new BigDecimal(0));
		List<Expense> expenses = new ArrayList<>();
		expenseRepository.findByUserId(id)
		.forEach(expenses::add);
		expenses.forEach((expense) -> {
			System.out.println(expense.getCategory());
			if(expense.getCategory().equals("FOOD"))
				categorySum.put("FOOD", expense.getSum());
			
			else if(expense.getCategory().equals("INSURANCE"))
				categorySum.put("INSURANCE", expense.getSum());
			
			else if(expense.getCategory().equals("TRAVEL"))
				categorySum.put("TRAVEL", expense.getSum());
			
			else if(expense.getCategory().equals("HOUSE"))
				categorySum.put("HOUSE", expense.getSum());
			
			else if(expense.getCategory().equals("RELAX"))
				categorySum.put("RELAX", expense.getSum());
			
			else if(expense.getCategory().equals("SHOPPING"))
				categorySum.put("SHOPPING", expense.getSum());
			
			else if(expense.getCategory().equals("OTHERS"))
				categorySum.put("OTHERS", expense.getSum());
			
			else System.out.println("There is no category");
		});
		return categorySum;
		
	}
}
