package org.example.userauthenticationservice_sept2024.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .anyRequest().permitAll()
                );
        //http
        //            .csrf(csrf -> csrf.disable())  // Disable CSRF protection (if needed)
        //            .authorizeHttpRequests((requests) -> requests
        //                .requestMatchers("/api/public/**").permitAll()  // Public URLs accessible without authentication
        //                .requestMatchers("/api/admin/**").hasRole("ADMIN")  // URLs restricted to users with ADMIN role
        //                .anyRequest().authenticated()  // All other requests require authentication
        //            );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}