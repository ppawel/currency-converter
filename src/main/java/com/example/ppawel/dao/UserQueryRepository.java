package com.example.ppawel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ppawel.model.UserQuery;

/**
 * Spring Data JPA repository for accessing user queries in the database.
 * 
 * @author ppawel
 *
 */
public interface UserQueryRepository extends JpaRepository<UserQuery, Long> {

	List<UserQuery> findByEmail(String email);
}
