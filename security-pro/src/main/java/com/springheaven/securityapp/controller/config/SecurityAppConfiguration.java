package com.springheaven.securityapp.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com")
public class SecurityAppConfiguration {

	// Setting up a view resolver for the controller we need to create an internal
	// page resolver bean to resolve this

	@Bean
	InternalResourceViewResolver viewResolver() {

		return new InternalResourceViewResolver("/WEB-INF/view/", ".jsp");
	}

}
