package com.example.springsecuritypractice.services;

import org.springframework.stereotype.Service;

import com.example.springsecuritypractice.models.Roles;

@Service
public class RolesPopulation {
	private final String ADMIN = "admin";

	public String processRoles(String role) {
		if (role.toLowerCase().contains(ADMIN.toLowerCase())) {
			return Roles.ROLE_ADMIN.toString();
		} else {
			return Roles.ROLE_USER.toString();
		}
	}

}
