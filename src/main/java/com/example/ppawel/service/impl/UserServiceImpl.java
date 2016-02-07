package com.example.ppawel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ppawel.dao.UserQueryRepository;
import com.example.ppawel.dao.UserRepository;
import com.example.ppawel.model.User;
import com.example.ppawel.model.UserAlreadyExistsException;
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

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserQueryRepository queryRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User register(UserRegistrationData data) throws UserAlreadyExistsException {
		// Check if user exists
		if (repository.findByEmail(data.getEmail()) != null) {
			throw new UserAlreadyExistsException();
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
