package com.devsuperior.dslearn.service;

import com.devsuperior.dslearn.config.AuthConfig;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.exceptions.UnauthorizedException;
import com.devsuperior.dslearn.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthService {

    private final AuthConfig authConfig;
   final
   UserRepository userRepository;

    public AuthService(UserRepository userRepository, AuthConfig authConfig) {
        this.userRepository = userRepository;
        this.authConfig = authConfig;
    }

    @Transactional(readOnly = true)
    public User authenticated(){
        String username = authConfig.getName();
        System.out.println("username: " + username);
        User user = userRepository.findByEmail(username)
               .orElseThrow(() -> new UnauthorizedException("usuário inválido"));
        user.setGrantedAuthorities();
        System.out.println("user: " + user.getEmail());
        return user;
    }
}
