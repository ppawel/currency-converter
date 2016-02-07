package com.example.ppawel.service;

import java.util.List;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.Errors;

import com.example.ppawel.model.User;
import com.example.ppawel.model.UserQuery;
import com.example.ppawel.model.UserRegistrationData;

/**
 * API for user related operations. Extends {@link UserDetailsService} so it can
 * be used together with {@link DaoAuthenticationProvider}.
 * 
 * @author ppawel
 *
 */
public interface UserService extends UserDetailsService {

	/**
	 * Attempts to register a new user - performs validation of input data,
	 * checks if user exists.
	 * 
	 * @param data
	 *            input data to use
	 * @param errors
	 *            used for recording errors during registration
	 * @return saved user or <code>null</code> if there were errors
	 * 
	 */
	User register(UserRegistrationData data, Errors errors);

	/**
	 * Lists saved user queries for the currently logged in user.
	 * 
	 * @return a list of queries or empty list if none found
	 */
	List<UserQuery> listUserQueries();
}
