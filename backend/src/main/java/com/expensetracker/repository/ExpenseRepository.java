package com.expensetracker.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracker.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	Page<Expense> findByUserIdAndCategory(Long userid, String category, Pageable page);

	Page<Expense> findByUserIdAndNameContaining(Long userid, String name, Pageable page);

	Page<Expense> findByUserId(Long userid, Pageable page);

	Optional<Expense> findByUserIdAndId(Long userid, Long id);
}
