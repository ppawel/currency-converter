package com.example.ppawel.spring;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.ppawel.dao")
@ComponentScan(basePackages = { "com.example.ppawel" })
@EntityScan("com.example.ppawel.model")
public class CoreConfig {

}
