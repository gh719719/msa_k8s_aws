package com.example.user.common.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration  // 서버 기동시 가장 먼저 읽어서 설정 (yaml도)
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // 1. CSRF 비활성화 (API 서버에서는 보통 끕니다)
            .csrf(csrf -> csrf.disable()) 
            
            // 2. HTTP 기본 인증 및 폼 로그인 비활성화 (JWT 사용 시 필요)
            .httpBasic(basic -> basic.disable())
            .formLogin(form -> form.disable())

            // 3. 경로별 권한 설정
            .authorizeHttpRequests(auth -> auth
                // 로그인, 회원가입, 건강체크는 누구나 가능하게 허용!
                .requestMatchers("/users/signIn", "/users/signUp", "/health-check").permitAll()
                // 그 외의 모든 요청은 로그인이 필요함
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
