package com.example.ppawel.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

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
	 * Attempts to register a new user.
	 * 
	 * @param data
	 *            input data to use
	 * @return saved user
	 * @throws ConstraintViolationException
	 *             when input data does not pass validation
	 */
	User register(UserRegistrationData data);

	/**
	 * Lists saved user queries for the currently logged in user.
	 * 
	 * @return a list of queries or empty list if none found
	 */
	List<UserQuery> listUserQueries();
}
