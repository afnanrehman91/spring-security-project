package com.example.springsecuritypractice.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springsecuritypractice.models.User;
import com.example.springsecuritypractice.repository.UserRepository;
import com.example.springsecuritypractice.services.RolesPopulation;

@Controller
@RequestMapping("/")
public class SecurityController {
	private BCryptPasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	private RolesPopulation rolesPopulation;

	/**
	 * @param passwordEncoder
	 * @param userRepository
	 * @param rolesPopulation
	 */
	public SecurityController(BCryptPasswordEncoder passwordEncoder, UserRepository userRepository,
			RolesPopulation rolesPopulation) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.rolesPopulation = rolesPopulation;
	}

	/**
	 * @return returns login page
	 */
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value = "user")
	public String helloUser() {
		return "user/index";
	}
	
	@GetMapping(value = "admin")
	public String helloAdmin() {
		return "admin/index";
	}

	/**
	 * @param model
	 * @return register page
	 */
	@GetMapping("register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "signuppages/register";
	}

	/**
	 * @param model
	 * @param user
	 * @return redirected url
	 */
	@PostMapping("register/save")
	public String saveUser(Model model, User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(rolesPopulation.processRoles(user.getRoles()));
		user.setEnabled(true);
		userRepository.save(user);
		return "redirect:/";
	}

}
