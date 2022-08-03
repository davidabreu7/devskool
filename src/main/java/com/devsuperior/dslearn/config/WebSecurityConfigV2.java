package com.devsuperior.dslearn.config;

import com.devsuperior.dslearn.security.JwtTokenVerifierFilter;
import com.devsuperior.dslearn.security.JwtUserPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigV2 {

    private final JwtConfig jwtConfig;

    private final AuthConfig authConfig;


    public WebSecurityConfigV2(JwtConfig jwtConfig, AuthConfig authConfig) {
        this.jwtConfig = jwtConfig;
        this.authConfig = authConfig;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.
                csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtLoginFilter(http))
                .addFilterAfter(new JwtTokenVerifierFilter(jwtConfig, authConfig), JwtUserPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers(HttpMethod.GET,"/users").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public JwtUserPasswordAuthenticationFilter jwtLoginFilter(HttpSecurity http) throws Exception {
        var filter = new JwtUserPasswordAuthenticationFilter(jwtConfig);
        filter.setAuthenticationManager(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)));
        filter.setFilterProcessesUrl("/login");
        return filter;
    }
}
