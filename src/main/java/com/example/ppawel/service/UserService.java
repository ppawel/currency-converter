package com.example.ppawel.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.ppawel.model.User;
import com.example.ppawel.model.UserRegistrationData;

public interface UserService extends UserDetailsService {

	User register(UserRegistrationData data);
}
