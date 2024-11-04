package com.springheaven.securityapp.controller.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity(debug = false)
@Configuration
public class SecurityAppConfig {

	// whenever we are implementing any security we need to validate again the users
	// we have in db or anywhere we are creating the user for this application to
	// access in this down below and the created user has to be stored in the
	// database
	@Bean
	public InMemoryUserDetailsManager userDetails() {

		List<SimpleGrantedAuthority> grantedauthority = new ArrayList<>();
		grantedauthority.add(new SimpleGrantedAuthority("user"));
		grantedauthority.add(new SimpleGrantedAuthority("admin"));
		UserDetails praveenUser = new User("praveenraj", "praveenraj", grantedauthority);
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		inMemoryUserDetailsManager.createUser(praveenUser);
		return inMemoryUserDetailsManager;

	}

	/*
	 * AuthenticationManager authenticationManager() { return }
	 */

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}
