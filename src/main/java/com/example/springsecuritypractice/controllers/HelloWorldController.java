package com.example.springsecuritypractice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@GetMapping(value = "/")
	public String helloEverybody() {
		return "Hello World!";
	}
}
