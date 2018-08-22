package com.ddobrowolski.budgetManager.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ddobrowolski.budgetManager.model.Expense;
import com.ddobrowolski.budgetManager.model.User;
import com.ddobrowolski.budgetManager.service.ExpenseService;

@RestController
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping("/users/{id}/expenses")
	public List<Expense> getAllExpenses(@PathVariable Long id){
		return expenseService.getAllExpenses(id);
	}
	
	@RequestMapping("/users/{id}/expenses/bydate/{dateString}")
	public List<Expense> findExpensesByDateString(@PathVariable Long id, @PathVariable String dateString){
		return expenseService.findByDateString(dateString);
	}
	
	@RequestMapping("/users/{id}/expenses/bydate/{dateString}/categorysum")
	public  Map<String, BigDecimal> getCategorySumByDateString(@PathVariable Long id, @PathVariable String dateString){
		return expenseService.getCategorySumByDate(dateString);
	}
	
	@RequestMapping("/users/{userId}/expenses/{id}")
	public Expense getExpense(@PathVariable Long id) {
		return expenseService.getExpense(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users/{userId}/expenses")
	public void addExpense(@RequestBody Expense expense, @PathVariable Long userId) {
		expense.setUser(new User(userId, "", "", new BigDecimal(0)));
		expenseService.addExpense(expense);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/users/{userId}/expenses/{id}")
	public void updateExpense(@RequestBody Expense expense, @PathVariable Long userId, @PathVariable Long id) {
		expense.setUser(new User(userId, "", "", new BigDecimal(0)));
		expenseService.updateExpense(expense);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{userId}/expenses/{id}")
	public void deleteExpense(@PathVariable Long id) {
		expenseService.deleteExpense(id);
	}
	
	@RequestMapping("/users/{id}/expenses/categorysum")
	public Map<String, BigDecimal> getCategorySum(@PathVariable Long id){
		return expenseService.getCategorySum(id);
	}
}	
