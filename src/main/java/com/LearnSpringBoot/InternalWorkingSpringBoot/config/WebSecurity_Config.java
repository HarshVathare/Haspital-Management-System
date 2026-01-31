package com.LearnSpringBoot.InternalWorkingSpringBoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity_Config {

    private final PasswordEncoder passwordEncoder;

    public WebSecurity_Config(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
        httpSecurity
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/public/**",
                                                 "/admin/**",
                                "/doctors/**",
                                "/patients/**"
                        ).permitAll()
//                                .requestMatchers("/admin/**").authenticated()
//                      .requestMatchers("/admin/**").authenticated()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/doctor/**").hasAnyRole("DOCTOR","ADMIN")
                );
//                .formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }


}
