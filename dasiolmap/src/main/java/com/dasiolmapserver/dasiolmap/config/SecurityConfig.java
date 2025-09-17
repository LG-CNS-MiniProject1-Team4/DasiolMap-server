package com.dasiolmapserver.dasiolmap.config;

// PasswordEncoder의 암호화 방식을 BCrypt로 지정하고, /api/user/signup 경로는 누구나 접근할 수 있도록 허용하는 역할을 합니다.

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy; // 👈 이 부분을 import 해주세요.
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
            // CSRF 보안 비활성화
            .csrf(csrf -> csrf.disable())
            
            // 세션을 사용하지 않는 STATELESS 정책 설정 (JWT 사용 시 필수)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            // HTTP Basic 및 Form Login 인증 방식 비활성화
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable())

            // API 경로별 접근 권한 설정
            .authorizeHttpRequests(authz -> authz
                // 아래 경로들은 인증 없이 누구나 접근 허용
                .requestMatchers(
                    "/api/v2/dasiolmap/user/**", 
                    "/api/v2/dasiolmap/store/**", 
                    "/api/v2/dasiolmap/search/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**"
                ).permitAll()
                // 그 외 모든 요청은 인증 필요
                .anyRequest().authenticated()
            );
            
        return http.build();
    }
}