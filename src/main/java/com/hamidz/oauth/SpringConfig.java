package com.hamidz.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Configuration
public class SpringConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {
        return http.
                authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/").permitAll();
                    registry.anyRequest().authenticated();
                }).
                oauth2Login(oauth2login -> {
                    oauth2login.successHandler((request,
                                                response,
                                                        authentication) ->
                            response.sendRedirect("/profile"));
                }).
                formLogin(Customizer.withDefaults()).
                build();
    }
}
