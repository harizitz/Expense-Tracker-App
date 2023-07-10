package com.expensetracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.expensetracker.entity.UserModel;
import com.expensetracker.exception.ItemAlreadyExistsException;
import com.expensetracker.exception.ResourceNotFoundException;
import com.expensetracker.repository.UserRepository;

@Service
public class ClientUserService {

	@Autowired
	private UserRepository userrepo;

	public void deleteUser() {
		Long id = loggedInUser().getId();
		if (userrepo.findById(id).get() == null) {
			throw new ResourceNotFoundException("No User found");
		}
		userrepo.deleteById(id);
	}

	public UserModel readUser() {
		Long id = loggedInUser().getId();
		return userrepo.findById(id).get();
	}

	public UserModel updateUser(UserModel user) {
		Long id = loggedInUser().getId();
		UserModel fetchedUser = userrepo.findById(id).get();
		fetchedUser.setId(id);
		fetchedUser.setName(user.getName());
		fetchedUser.setEmail(user.getEmail());
		fetchedUser.setAge(user.getAge());
		fetchedUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return userrepo.save(fetchedUser);
	}

	public UserModel registerUser(UserModel user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		if (userrepo.findByEmail(user.getEmail()) != null) {
			throw new ItemAlreadyExistsException("Email already exists! Please Login");
		}
		return userrepo.save(user);
	}

	public UserModel loggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (userrepo.findByEmail(authentication.getName()) != null) {
			return userrepo.findByEmail(authentication.getName());
		}
		throw new ResourceNotFoundException("User Not found");
	}

}
