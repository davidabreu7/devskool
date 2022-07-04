package com.devsuperior.dslearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public JwtUserPasswordAuthenticationFilter jwtUserPasswordAuthenticationFilter(){
//        return new JwtUserPasswordAuthenticationFilter();
//    }

}
