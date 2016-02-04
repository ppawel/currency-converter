package com.example.ppawel.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.ppawel.service.UserService;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.ppawel.dao")
@ComponentScan(basePackages = { "com.example.ppawel" })
@EntityScan("com.example.ppawel.model")
@EnableWebSecurity
public class CoreConfig {

	@Autowired
	private UserService userService;

	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	AuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userService);
		return provider;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
