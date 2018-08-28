package com.ddobrowolski.budgetManager.controller;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ddobrowolski.budgetManager.model.User;
import com.ddobrowolski.budgetManager.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerUTest {
	
	private MockMvc mvc;
	
	@Mock
	private UserService userService;
	
	@InjectMocks
	private UserController userController;
	private ObjectMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new ObjectMapper();
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	
	@Test
	void createUser() throws Exception{
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setEmail("test@test.com");
		user.setMonthBudget(new BigDecimal(0));
		
		mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);
		
        given(userService.save(user)).willReturn(user);
        
        mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isOk());
	}
}
