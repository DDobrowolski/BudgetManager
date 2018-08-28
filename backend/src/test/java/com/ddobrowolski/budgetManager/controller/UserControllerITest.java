package com.ddobrowolski.budgetManager.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.ddobrowolski.budgetManager.model.User;
import com.ddobrowolski.budgetManager.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerITest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MockMvc mvc;
	@Autowired
	ObjectMapper mapper;
	
	@BeforeEach
	void setUp() {
		mapper = new ObjectMapper();
	}
	
	@AfterEach
	void tearDown() {
		userRepository.deleteAll();
	}
	
	@Test
	void createUser() throws Exception{
		User user = new User();
		user.setUsername("user1");
		user.setPassword("123");
		user.setEmail("test@test.com");
		user.setMonthBudget(new BigDecimal(0));
		
		String userJson = mapper.writeValueAsString(user);
		
		mvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson)
                .with(csrf()))
                .andExpect(status().isOk());
		
		User found = userRepository.findByUsername("user1");
		
		assertNotNull(found);
		assertNotNull(found.getId());
			
	}
	
	@Test
	void getUser() throws Exception {
		User user = new User();
		user.setUsername("user1");
		user.setPassword("123");
		user.setEmail("test@test.com");
		user.setMonthBudget(new BigDecimal(0));
		userRepository.save(user);
		
		MvcResult result = mvc.perform(get("/users/{id}", user.getId()))
				.andExpect(status().isOk())
				.andReturn();
		
		User found = mapper.readValue(result.getResponse().getContentAsString(), User.class);
		
		assertEquals(user.getUsername(), found.getUsername());
	}
}
