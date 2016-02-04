package com.example.ppawel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ppawel.dao.UserRepository;
import com.example.ppawel.model.User;
import com.example.ppawel.model.UserRegistrationData;
import com.example.ppawel.service.UserService;

/**
 * {@link UserService} implementation backed by Spring Data JPA user repository.
 * 
 * @author ppawel
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User register(UserRegistrationData data) {
		User user = new User();

		user.setEmail(data.getEmail());
		user.setPassword(passwordEncoder.encode(data.getPassword()));

		user = repository.save(user);

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByEmail(username);
	}

}
