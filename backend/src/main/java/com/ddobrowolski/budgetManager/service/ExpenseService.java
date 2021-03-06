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
	BigDecimal monthSum;

	
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM.yyyy");
		Calendar now = Calendar.getInstance();
		String dateString = dateFormat.format(now.getTime()).toString();
		String monthString = monthFormat.format(now.getTime()).toString();
		expense.setDateString(dateString);
		expense.setMonthString(monthString);
		
		if(expense.getSum().intValue()<0) 
			expense.setSum(new BigDecimal(0));
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
	
	public List<Expense> findByDateString(String dateString, Long userId) {
		return expenseRepository.findByDateStringAndUserId(dateString, userId);
	}
	
	public Map <String, BigDecimal> getCategorySum (Long id) {
		Map <String, BigDecimal> categorySum = new HashMap<>();
		List<Expense> expenses = new ArrayList<>();
		addStartingValues(categorySum);
		expenseRepository.findByUserId(id)
		.forEach(expenses::add);
		expenses.forEach((expense) -> {
			if (categorySum.containsKey(expense.getCategory().name())) {
				categorySum.put(expense.getCategory().name(), categorySum.get(expense.getCategory().name()).add(expense.getSum()));
			}
			else System.out.println("There is no category");
		});
		return categorySum;
	}
	
	public Map <String, BigDecimal> getCategorySumByDate (String dateString, Long userId) {
		Map <String, BigDecimal> categorySum = new HashMap<>();
		List<Expense> expenses = new ArrayList<>();
		addStartingValues(categorySum);
		expenseRepository.findByDateStringAndUserId(dateString, userId)
		.forEach(expenses::add);
		expenses.forEach((expense) -> {
			if (categorySum.containsKey(expense.getCategory().name())) {
				categorySum.put(expense.getCategory().name(), categorySum.get(expense.getCategory().name()).add(expense.getSum()));
			}
			else System.out.println("There is no category");
		});
		return categorySum;
	}
	
	private void addStartingValues(Map<String, BigDecimal> map) {
		map.put("FOOD", new BigDecimal(0));
		map.put("INSURANCE", new BigDecimal(0));
		map.put("TRAVEL", new BigDecimal(0));
		map.put("HOUSE", new BigDecimal(0));
		map.put("RELAX", new BigDecimal(0));
		map.put("SHOPPING", new BigDecimal(0));
		map.put("OTHERS", new BigDecimal(0));
	}

	public List<Expense> getExpenseByMonth(String monthString, Long userId){
		return expenseRepository.findByMonthStringAndUserId(monthString, userId);
	}
	public BigDecimal getExpensesSumByMonth(String monthString, Long userId) {
		monthSum = new BigDecimal(0);
		List<Expense> expenses = new ArrayList<>();
		expenses = expenseRepository.findByMonthStringAndUserId(monthString, userId);
		expenses.forEach((expense) -> {
			monthSum = monthSum.add(expense.getSum());
		});
		return monthSum;
	}
}
