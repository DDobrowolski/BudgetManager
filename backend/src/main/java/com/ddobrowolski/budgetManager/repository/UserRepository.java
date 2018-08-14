package com.ddobrowolski.budgetManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ddobrowolski.budgetManager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
