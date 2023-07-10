package com.expensetracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.expensetracker.entity.Expense;
import com.expensetracker.exception.ResourceNotFoundException;
import com.expensetracker.repository.ExpenseRepository;

@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepo;

	@Autowired
	private ClientUserService userService;

	public Expense addExpense(Expense expense) {
		expense.setUser(userService.loggedInUser());
		return expenseRepo.save(expense);
	}

	public Page<Expense> getAllExpenses(Pageable page) {
		return expenseRepo.findByUserId(userService.loggedInUser().getId(), page);
	}

	public Expense updateExpense(Expense expense, Long id) {
		Expense updatedExpense = getExpenseById(id);
		updatedExpense.setCategory(expense.getCategory());
		updatedExpense.setDescription(expense.getDescription());
		updatedExpense.setAmount(expense.getAmount());
		updatedExpense.setName(expense.getName());
		return expenseRepo.save(updatedExpense);
	}

	public Expense getExpenseById(Long id) {
		Long userid = userService.loggedInUser().getId();
		Optional<Expense> expense = expenseRepo.findByUserIdAndId(userid, id);
		if (expense.isPresent()) {
			return expense.get();
		} else {
			throw new ResourceNotFoundException("Expense Not found for the ID : " + id);
		}
	}

	public void deleteExpense(Long id) {
		Expense expenseById = getExpenseById(id);
		expenseRepo.delete(expenseById);
	}

	public List<Expense> readExpensesByCategory(String category, Pageable page) {
		Long userid = userService.loggedInUser().getId();
		return expenseRepo.findByUserIdAndCategory(userid, category, page).toList();
	}

	public List<Expense> readExpensesByName(String name, Pageable page) {
		Long userid = userService.loggedInUser().getId();
		return expenseRepo.findByUserIdAndNameContaining(userid, name, page).toList();
	}
}
