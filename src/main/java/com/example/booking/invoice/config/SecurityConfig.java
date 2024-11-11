package com.example.booking.invoice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().authenticated()
                ) .oauth2Login(oauth2Login -> oauth2Login
                        .defaultSuccessUrl("/index.html", true) // Redirect to /index.html page on successful login
                );

        return http.build();
    }

    @Bean
    WebSecurityCustomizer ignoringCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/h2-console/**", "/api/invoice/save"
        );
    }
}