package com.ddobrowolski.budgetManager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddobrowolski.budgetManager.model.User;
import com.ddobrowolski.budgetManager.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers(){
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public User getUser(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public void addUser(User user) {
		userRepository.save(user);
	}

	public void updateUser(Long id, User user) {
		userRepository.save(user);
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);

	}
	
	public User find(String username) {
		return userRepository.findByUsername(username);
	}
	
	public User save(User user) {
		return userRepository.saveAndFlush(user);
	}
}
