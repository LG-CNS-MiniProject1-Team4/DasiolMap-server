package com.dasiolmapserver.dasiolmap.config;

// PasswordEncoder의 암호화 방식을 BCrypt로 지정하고, /api/user/signup 경로는 누구나 접근할 수 있도록 허용하는 역할을 합니다.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/v2/dasiolmap/user/**", "/api/v2/dasiolmap/store/**", "/api/v2/dasiolmap/search/**").permitAll()
            .anyRequest().authenticated()
            );
        return http.build();
    }
}