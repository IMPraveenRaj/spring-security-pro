package com.springheaven.securityapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

	// hi
	@GetMapping(value = "/hi")
	public String sayHi() {
		return "say Hi";
	}

}
