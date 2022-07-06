package com.devsuperior.dslearn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.logging.Logger;

@Configuration
public class AppConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Logger logger() {
        return Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    }

    @Bean
    public AuthConfig authConfig() {
        return new AuthConfig();
    }


//    @Bean
//    public JwtUserPasswordAuthenticationFilter jwtUserPasswordAuthenticationFilter(){
//        return new JwtUserPasswordAuthenticationFilter();
//    }

}
