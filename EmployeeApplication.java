package com.example.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.example.employee")
@EnableJpaRepositories("com.example.employee")
@ComponentScan(basePackages= {"com.example.employee"})
@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		System.out.println("Welcome");
		SpringApplication.run(EmployeeApplication.class, args);
	}
}