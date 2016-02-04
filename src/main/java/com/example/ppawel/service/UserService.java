package com.example.ppawel.service;

import com.example.ppawel.model.User;
import com.example.ppawel.model.UserRegistrationData;

public interface UserService {

	User register(UserRegistrationData data);
}
