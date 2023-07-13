package com.expensetracker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.config.CustomUserDetailsService;
import com.expensetracker.config.JwtUtil;
import com.expensetracker.entity.JwtResponse;
import com.expensetracker.entity.LoginModel;
import com.expensetracker.entity.UserModel;
import com.expensetracker.service.ClientUserService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private ClientUserService clientUserService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	// Add User
	@PostMapping("/register")
	public ResponseEntity<UserModel> addUser(@Valid @RequestBody UserModel user) {
		return new ResponseEntity<>(clientUserService.registerUser(user), HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody LoginModel user) {
		// compares user entered credentials with load by user name credentials
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
		} catch (DisabledException e) {
			throw new DisabledException("User Disabled");
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid Credentials");
		}
		// after authenticated it will use details from db to create token
		final String token = jwtUtil.generateToken(customUserDetailsService.loadUserByUsername(user.getEmail()));

		// token is returned as response
		return new ResponseEntity<JwtResponse>(new JwtResponse(token), HttpStatus.OK);
	}

	// Update User
	@PutMapping("/profile")
	public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) {
		return new ResponseEntity<>(clientUserService.updateUser(user), HttpStatus.OK);
	}

	// Read User
	@GetMapping("/profile")
	public ResponseEntity<UserModel> getUser() {
		return new ResponseEntity<>(clientUserService.readUser(), HttpStatus.OK);
	}

	// Delete User
	@DeleteMapping("/deactivate")
	public ResponseEntity<String> deleteUser() {
		clientUserService.deleteUser();
		return new ResponseEntity<>("Deleted", HttpStatus.OK);
	}
}
