package com.devsuperior.dslearn.config;

import com.devsuperior.dslearn.security.JwtTokenVerifierFilter;
import com.devsuperior.dslearn.security.JwtUserPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;
    private final Environment env;
    final BCryptPasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(JwtConfig jwtConfig, BCryptPasswordEncoder passwordEncoder, Environment env, UserDetailsService userDetailsService) {
        this.jwtConfig = jwtConfig;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
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
        filter.setFilterProcessesUrl("/oauth/token");
        return filter;
    }

     @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtLoginFilter())
                .addFilterAfter(new JwtTokenVerifierFilter(jwtConfig), JwtUserPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated();

//                .antMatchers(HttpMethod.GET, "/cities/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/events/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/events/**").authenticated()
//                .antMatchers(HttpMethod.POST, "/events").authenticated()
//                .antMatchers(HttpMethod.POST,"/cities/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/cities").hasRole("ADMIN");

    }
}
