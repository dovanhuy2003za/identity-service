package com.huydo.identity_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        .csrf(csrf -> csrf.disable()) // Tắt CSRF protection
        .authorizeHttpRequests(requests ->
            requests
                .requestMatchers(HttpMethod.POST, "/user/**").permitAll() // Cho phép truy cập /user/
               
                .anyRequest().authenticated() // Các endpoint khác yêu cầu xác thực
        );

        return httpSecurity.build();
    }
}
