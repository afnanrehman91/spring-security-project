package com.example.springsecuritypractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.springsecuritypractice.repository.UserRepository;

/**
 * This is a sample application with functionality of Login, Logout, Register and Remember me.
 * 
 * It is coded using following stack :
 * 1) Spring Security form based authentication
 * 2) Spring Data JPA
 * 3) Spring Rest
 * 4) SSL certificate
 * 5) BcryptEncoder
 * 6) MySql
 * 7) JSP's
 **/
@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
}
