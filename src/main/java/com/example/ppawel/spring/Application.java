package com.example.ppawel.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//e48903f3d4ee44e28398792694cd3b77
@Configuration
@EnableAutoConfiguration
@Import({ CoreConfig.class, SecurityConfig.class, WebConfig.class })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}