package com.springheaven.securityapp.controller.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityAppConfig {

	// lets create the user using the builder

	@Autowired
	private HttpSecurity httpSecurity;

	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

		UserDetails praveenUse = User.withUsername("praveenraj")
				.password("$2a$10$z8RT.3q2QcGSK1sfi0Juc.5fjOMJ34c3vsmOaxnUonttzl7z1fU/a").roles("user", "admin")
				.build();

		UserDetails dharshiniUser = User.withUsername("dharshini")
				.password("$2a$10$9aw9N3aCMDJnUZI9TlQdVe/sl0cfKMpyFCOO/L0E4mM8VT6IGpQnK").roles("user", "admin")
				.build();

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
	public SecurityFilterChain settingUpHttpSecurity() throws Exception {

		/*
		 * this is the way to write the actual security code DefaultSecurityFilterChain
		 * defaultSecurityFilterChain =this.httpSecurity.
		 * authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
		 * .build();
		 */
		// below code written was deprecated in spring 6.1 version

		// to permit anyrequest we dont need authentication
		// this.httpSecurity.authorizeHttpRequests().anyRequest().permitAll();

		// authenticate the incoming request
		// this.httpSecurity.authorizeHttpRequests(). anyRequest().authenticated();

		// deny all the incoming request
		// this.httpSecurity.authorizeHttpRequests().anyRequest().denyAll();

		// but we have a functionality to be enabled in this for every request
		// hi should be authenticated by should be denied // hello will be permitted by
		// all
		// httpSecurity.authorizeHttpRequests().requestMatchers("/hi").authenticated();
		// httpSecurity.authorizeHttpRequests().requestMatchers("/hello").permitAll();
		// httpSecurity.authorizeHttpRequests().requestMatchers("/bye").denyAll();
		// httpSecurity.authorizeHttpRequests().anyRequest().permitAll();

		httpSecurity.authorizeHttpRequests((authorize) -> {
			authorize.anyRequest().authenticated();
			authorize.requestMatchers("/hello").permitAll();
			authorize.requestMatchers("/bye").denyAll();
		});

		httpSecurity.formLogin(Customizer.withDefaults());
		httpSecurity.httpBasic(Customizer.withDefaults());
//		httpSecurity.formLogin();
		// httpSecurity.httpBasic();

		return httpSecurity.build();

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

	@Bean(name = "mvcHandlerMappingIntrospector")
	HandlerMappingIntrospector handlerMappingIntrospector() {
		return new HandlerMappingIntrospector();
	}

}
