package com.springheaven.securityapp.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityAppConfig {

	// lets create the user using the builder
	
	@Autowired
    private HttpSecurity httpSecurity;

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

		UserDetails praveenUse = User.withUsername("praveenraj").password("$2a$10$z8RT.3q2QcGSK1sfi0Juc.5fjOMJ34c3vsmOaxnUonttzl7z1fU/a").roles("user", "admin").build();

		UserDetails dharshiniUser = User.withUsername("dharshini").password("$2a$10$9aw9N3aCMDJnUZI9TlQdVe/sl0cfKMpyFCOO/L0E4mM8VT6IGpQnK").roles("user", "admin").build();

		return new InMemoryUserDetailsManager(praveenUse, dharshiniUser);
	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain() throws Exception {
		
		/*this is the way to write the actual security code 
		 * DefaultSecurityFilterChain defaultSecurityFilterChain =this.httpSecurity.
		 * authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
		 * .build();
		 */
	     this.httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
		this.httpSecurity.formLogin();
		this.httpSecurity.httpBasic();
		
		return httpSecurity.
				build();
		
	}


	// whenever we are implementing any security we need to validate again the users
	// we have in db or anywhere we are creating the user for this application to
	// access in this down below and the created user has to be stored in the
	// database
	/*
	 * @Bean public InMemoryUserDetailsManager userDetails() {
	 * 
	 * List<SimpleGrantedAuthority> grantedauthority = new ArrayList<>();
	 * grantedauthority.add(new SimpleGrantedAuthority("user"));
	 * grantedauthority.add(new SimpleGrantedAuthority("admin")); UserDetails
	 * praveenUser = new User("praveenraj", "praveenraj", grantedauthority);
	 * InMemoryUserDetailsManager inMemoryUserDetailsManager = new
	 * InMemoryUserDetailsManager();
	 * inMemoryUserDetailsManager.createUser(praveenUser); return
	 * inMemoryUserDetailsManager;
	 * 
	 * }
	 */

}
