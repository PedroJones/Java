package com.exemple.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
	
	@Bean
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder(10); //Método que criptografa a senha.
	}
}
