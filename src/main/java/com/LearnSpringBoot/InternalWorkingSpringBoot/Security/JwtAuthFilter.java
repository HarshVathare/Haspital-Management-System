package com.LearnSpringBoot.InternalWorkingSpringBoot.Security;

import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.User;
import com.LearnSpringBoot.InternalWorkingSpringBoot.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j //for logging purpose
public class JwtAuthFilter extends OncePerRequestFilter {

    private final AuthUtill authUtill;
    private final UserRepository userRepository;

    public JwtAuthFilter(AuthUtill authUtill, UserRepository userRepository) {
        this.authUtill = authUtill;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("incoming request ..!"); request.getRequestURI();
        final String requestTokenHeader = request.getHeader("Authorization"); //getting Token data

        if(requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String Token = requestTokenHeader.split("Bearer ")[1]; //store only 1th index data
        String username = authUtill.getUsernameFromToken(Token); //getting username

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findByUsername(username).orElseThrow();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
