package com.springheaven.securityapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springheaven.securityapp.dto.RegistrationDTO;

@Controller
public class RegistrationController {

	// to avoid the cicurar dependency we did the following changes
	@Autowired
	@Lazy
	InMemoryUserDetailsManager inMemoryUserDetailsManager;

	@Autowired
	PasswordEncoder bcryptPasswordEncoder;

	/*
	 * public RegistrationController(InMemoryUserDetailsManager
	 * inMemoryUserDetailsManager) {
	 * this.inMemoryUserDetailsManager=inMemoryUserDetailsManager; }
	 */
	@GetMapping("/register")
	public String showRegistraionPage(@ModelAttribute("reg") RegistrationDTO registrationDTO) {

		return "register";
	}
	/*
	 * @ResponseBody
	 * 
	 * @GetMapping("/process-registration") public String
	 * processRegistraionPage(@RequestParam("username") String
	 * user, @RequestParam("password") String pass) {
	 * 
	 * UserDetails newUser =
	 * User.withUsername(user).password(bcryptPasswordEncoder.encode(pass)).roles(
	 * "user") .build(); inMemoryUserDetailsManager.createUser(newUser);
	 * 
	 * return "registration Successfull"; }
	 */
	

	@ResponseBody
	@PostMapping("/process-registration")
	public String processRegistrationPage(RegistrationDTO registrationDTO) {

		
		String encodedPassword = bcryptPasswordEncoder.encode(registrationDTO.getPassword());
		
		UserDetails appUser = User
				.withUsername(registrationDTO.getUsername())
				.password(encodedPassword)
				.roles("user")
				.build();
		
		inMemoryUserDetailsManager.createUser(appUser);
		///jdbcUserDetailsManager.createUser(appUser);
		
		
		return "registration sucessful for the user " + registrationDTO.getUsername();
	}
	
}
