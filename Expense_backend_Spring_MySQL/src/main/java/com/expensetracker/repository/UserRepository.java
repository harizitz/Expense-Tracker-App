package com.expensetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.entity.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
	
	UserModel findByEmail(String email);

}
