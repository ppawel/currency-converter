package com.example.ppawel.test.cucumber;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.example.ppawel.spring.CoreConfig;
import com.example.ppawel.spring.SecurityConfig;

@Configuration
@EnableAutoConfiguration
@Import({ CoreConfig.class, SecurityConfig.class })
public class TestConfig {
	@Bean
	public Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}
}
