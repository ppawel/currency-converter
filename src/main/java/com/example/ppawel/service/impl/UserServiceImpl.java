package com.example.ppawel.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.ppawel.dao.UserQueryRepository;
import com.example.ppawel.dao.UserRepository;
import com.example.ppawel.model.User;
import com.example.ppawel.model.UserQuery;
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

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserQueryRepository queryRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private Validator validator;

	@Override
	public User register(UserRegistrationData data, Errors errors) {
		// Validate the data transfer object
		validator.validate(data, errors);

		// Check if user exists
		if (repository.findByEmail(data.getEmail()) != null) {
			errors.rejectValue("email", "user-exists", "User already exists");
		}

		if (errors.hasErrors()) {
			log.error("Registration failed with errors  {}", errors);
			return null;
		}

		User user = new User();

		user.setEmail(data.getEmail());
		user.setPassword(passwordEncoder.encode(data.getPassword()));
		user.setBirthDate(data.getBirthDate());
		user.setStreet(data.getStreet());
		user.setCity(data.getCity());
		user.setCountryCode(data.getCountryCode());

		user = repository.save(user);

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return user;
	}

	@Override
	public List<UserQuery> listUserQueries() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return queryRepository.findByEmail(email);
	}

}
