package com.LearnSpringBoot.InternalWorkingSpringBoot.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity_Config {

    private final PasswordEncoder passwordEncoder;
    private final JwtAuthFilter jwtAuthFilter;

    public WebSecurity_Config(PasswordEncoder passwordEncoder, JwtAuthFilter jwtAuthFilter) {
        this.passwordEncoder = passwordEncoder;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionConfig->sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth->auth
                .requestMatchers("/public/**","/auth/**").permitAll()
                                .requestMatchers("/admin/**").authenticated()
                                .anyRequest().authenticated()
//                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/doctor/**").hasAnyRole("ADMIN", "DOCTOR")
        )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();
    }

}
