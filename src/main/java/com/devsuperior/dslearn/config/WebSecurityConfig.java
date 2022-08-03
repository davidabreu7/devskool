package com.devsuperior.dslearn.config;

import com.devsuperior.dslearn.security.JwtUserPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;

    @Autowired
    private AuthConfig authConfig;

    final BCryptPasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(JwtConfig jwtConfig, BCryptPasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.jwtConfig = jwtConfig;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public JwtUserPasswordAuthenticationFilter jwtLoginFilter() throws Exception {
        var filter = new JwtUserPasswordAuthenticationFilter(jwtConfig);
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setFilterProcessesUrl("/login");
        return filter;
    }

//     @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.
//                csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilter(jwtLoginFilter())
//                .addFilterAfter(new JwtTokenVerifierFilter(jwtConfig, authConfig), JwtUserPasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/actuator/**").permitAll()
//                .antMatchers(HttpMethod.GET,"/users").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/oauth/token").permitAll()
//                .anyRequest().authenticated();
//
//
//    }
}
