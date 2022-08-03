package com.devsuperior.dslearn.security;

import com.devsuperior.dslearn.config.AuthConfig;
import com.devsuperior.dslearn.config.JwtConfig;
import com.devsuperior.dslearn.controller.exception.AuthorizationError;
import com.devsuperior.dslearn.dto.UserAuthenticationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;



public class JwtUserPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final JwtConfig jwtConfig;
    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private AuthConfig authConfig;

    @Autowired
    ObjectMapper objectMapper;

    public JwtUserPasswordAuthenticationFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            UserAuthenticationDto authenticationUser = new ObjectMapper().readValue(request.getInputStream(), UserAuthenticationDto.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationUser.getEmail(), authenticationUser.getPassword());

            response.addHeader("email", authenticationUser.getEmail());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException {
        authConfig.setName(response.getHeader("email"));
        System.out.println(response.getHeader("email"));
        System.out.println(authConfig.getName());
        SecretKey secretKey = jwtConfig.getSecretKeyForSigning();
        String token = Jwts.builder()
                .setSubject(response.getHeader("email"))
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationWeeks())))
                .signWith(secretKey)
                .compact();
        String jsonString = "{\"Authorization\": \"Bearer " + token + "\"}";
        response.setContentType("application/json");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        response.getWriter().write(jsonString);

        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        AuthorizationError errorResponse = new AuthorizationError( "Unauthorized", failed.getMessage());
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
