package com.exemple.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/*Configurações do security*/

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity 
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	   
	private final PasswordEncoder passwordEncoder;

	@Autowired
	    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
	        this.passwordEncoder = passwordEncoder;
	    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests() /*Todas as resquisições precisarão ser autenticadas utilizando uma autorização básica.*/
		.antMatchers("/")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails pedroJonesUser = User.builder()
			.username("PedroJones")
			.password(passwordEncoder.encode("12345678"))
			.roles(ApplicationUserRole.STUDENT.name())
			.build();
		
		UserDetails adminUser = User.builder()
			.username("admin")
			.password(passwordEncoder.encode("admin"))
			.roles(ApplicationUserRole.ADMIN.name())
			.build();
		
		return new InMemoryUserDetailsManager(
				pedroJonesUser,
				adminUser
				);
	}
	
	
	
}
