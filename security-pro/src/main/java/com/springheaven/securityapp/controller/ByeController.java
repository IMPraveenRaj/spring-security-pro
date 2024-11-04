package com.springheaven.securityapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ByeController {

	// hi
	@GetMapping(value = "/bye")
	public String sayBye() {
		return "say Bye";
	}

}
