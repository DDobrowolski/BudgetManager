package com.ddobrowolski.budgetManager.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ddobrowolski.budgetManager.model.Expense;
import com.ddobrowolski.budgetManager.model.User;
import com.ddobrowolski.budgetManager.service.ExpenseService;
import com.fasterxml.jackson.databind.ObjectMapper;

class ExpenseControllerTest {

	private MockMvc mvc;
	
	@Mock
	private ExpenseService expenseService;
	@InjectMocks
	ExpenseController expenseController;
	private ObjectMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new ObjectMapper();
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(expenseController).build();
	}
	
	void addExpense() throws Exception {
		Expense expense = new Expense();
		User user = new User(new Long (1), "user", "userpass", new BigDecimal(100), "user@user.com");
		expense.setName("expense");
		expense.setSum(new BigDecimal(15));
		expense.setUser(user);
		
		mapper = new ObjectMapper();
		String expenseJson = mapper.writeValueAsString(expense);
		    
	        mvc.perform(post("/users/"+user.getId()+"/expenses")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(expenseJson))
	                .andExpect(status().isOk());
		
	}

}
