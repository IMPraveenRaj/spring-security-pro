package com.springheaven.securityapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

	@GetMapping("/register")
	public String showRegistraionPage() {

		return "register";
	}

}
