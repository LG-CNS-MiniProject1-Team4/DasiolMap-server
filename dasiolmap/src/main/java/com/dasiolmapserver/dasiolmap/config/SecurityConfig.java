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
                .requestMatchers("/api/user/**", "/api/tags/**", "/api/stores/**").permitAll()
            .anyRequest().authenticated()
            );
        return http.build();
    }
}

// .csrf(csrf -> csrf.disable())   // CSRF 보호 비활성화 (개발 초기 단계)
// .anyRequest().authenticated() // 나머지 모든 경로는 인증 필요
// .requestMatchers("/api/user/signup").permitAll() // '/api/user/signup' 경로는 인증 없이 허용