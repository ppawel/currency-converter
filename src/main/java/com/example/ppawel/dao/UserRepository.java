package com.example.ppawel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ppawel.model.User;

/**
 * Spring Data JPA repository for accessing users in the database.
 * 
 * @author ppawel
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
