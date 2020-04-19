package com.example.springsecuritypractice.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springsecuritypractice.models.UserPrinciple;
import com.example.springsecuritypractice.models.User;
import com.example.springsecuritypractice.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	private UserRepository userRepository;

	public MyUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByusername(userName);
		user.orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND : " + userName));
		return user.map(UserPrinciple::new).get();
	}

}
