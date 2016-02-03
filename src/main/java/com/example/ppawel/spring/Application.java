package com.example.ppawel.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//e48903f3d4ee44e28398792694cd3b77
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.example.ppawel" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}