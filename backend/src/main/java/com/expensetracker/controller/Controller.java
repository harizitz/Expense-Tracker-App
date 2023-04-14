package com.expensetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.entity.Expense;
import com.expensetracker.service.ExpenseService;


@RestController
@CrossOrigin(origins = "*")
public class Controller {

	@Autowired
	private ExpenseService expenseService;

	//test
	@GetMapping("/test")
	public String welcome() {
		return "Welcome !!!";
	}

	// Add Expense
	@PostMapping("/addExpense")
	public ResponseEntity<Expense> addExpense(@RequestBody Expense expense) {
		return new ResponseEntity<>(expenseService.addExpense(expense), HttpStatus.OK);
	}

	// Update Expense
	@PutMapping("/update/{id}")
	public ResponseEntity<Expense> updateExpense(@RequestBody Expense expense, @PathVariable long id) {
		return new ResponseEntity<>(expenseService.updateExpense(expense, id), HttpStatus.OK);
	}

	// View All Expenses
	@GetMapping("/expenses")
	public List<Expense> getAllExpenses(Pageable page) {
		return expenseService.getAllExpenses(page).toList();
	}

	// Filter by category
	@GetMapping("/expense/category")
	public List<Expense> getAllExpensesByCategory(@RequestParam String category, Pageable page) {
		return expenseService.readExpensesByCategory(category, page);
	}

	// Filter by name
	@GetMapping("/expense/name")
	public List<Expense> getAllExpensesByName(@RequestParam String name, Pageable page) {
		return expenseService.readExpensesByName(name, page);
	}

	// Get Expense
	@GetMapping("/expense/{id}")
	public ResponseEntity<Expense> getExpense(@PathVariable Long id) {
		return new ResponseEntity<>(expenseService.getExpenseById(id), HttpStatus.OK);
	}

	// Delete Expense
	@DeleteMapping("/expense/{id}")
	public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
		expenseService.deleteExpense(id);
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}

}
