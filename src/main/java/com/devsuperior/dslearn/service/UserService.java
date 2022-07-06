package com.devsuperior.dslearn.service;

import com.devsuperior.dslearn.dto.UserDto;
import com.devsuperior.dslearn.entities.User;
import com.devsuperior.dslearn.exceptions.ResourceNotFoundException;
import com.devsuperior.dslearn.exceptions.UnauthorizedException;
import com.devsuperior.dslearn.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

   private final Logger logger;
   private final AuthService authService;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, AuthService authService, Logger logger) {
        this.userRepository = userRepository;

        this.authService = authService;
        this.logger = logger;
    }
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {

        validateSelfOrAdmin(id);

        return userRepository.findById(id)
                .map(UserDto::new)
                .orElseThrow(() -> new ResourceNotFoundException("Resource id: %d not found".formatted(id)));
    }

    private void validateSelfOrAdmin(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource id: %d not found".formatted(userId)));
        logger.info("user: " + user.getId());

        User authenticated = authService.authenticated();

        if (!user.getId().equals(authenticated.getId()) && !authenticated.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new UnauthorizedException("Usuário não autorizado");
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        user.setGrantedAuthorities();

        return user;
    }
}
