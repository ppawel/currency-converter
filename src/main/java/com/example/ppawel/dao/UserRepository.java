package com.example.ppawel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ppawel.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
