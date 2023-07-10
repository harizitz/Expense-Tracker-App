package com.expensetracker.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.expensetracker.entity.UserModel;
import com.expensetracker.exception.ResourceNotFoundException;
import com.expensetracker.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel userFromDB = userrepo.findByEmail(email);
		if (userFromDB != null) {
			return new User(userFromDB.getEmail(), userFromDB.getPassword(), new ArrayList<>());
		}
		throw new ResourceNotFoundException("User not found");
	}

}
