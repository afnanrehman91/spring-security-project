package com.example.springsecuritypractice.securityconfigs;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private BCryptPasswordEncoder passwordEncoder;
	private UserDetailsService userDetailsService;

	public SecurityConfig(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin").hasRole("ADMIN")
								.antMatchers("/user").hasAnyRole("USER", "ADMIN")
								.antMatchers("/", "/**").permitAll()
								.and()
								.formLogin()
				                .loginProcessingUrl("/signin")
				                .loginPage("/login").permitAll()
				                .usernameParameter("txtUsername")
				                .passwordParameter("txtPassword")
				                .and()
				                .logout()
				                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				                .clearAuthentication(true)
				                .invalidateHttpSession(true)
				                .deleteCookies("JSESSIONID","remember-me")
				                .logoutSuccessUrl("/login")
				                .and()
				                .rememberMe()
				                .tokenValiditySeconds(2592000)
				                .key("Secret")
				                .rememberMeParameter("checkRememberMe");	                
	}
}
