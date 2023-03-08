package com.is.projektbackend.projekt.application;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000/")
@SpringBootApplication
@EntityScan(basePackages = { "com.is.projektbackend.projekt.application.model" })
public class ProjektBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjektBackendApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new SCryptPasswordEncoder(16384, 8, 1, 16,8);
    }
}
