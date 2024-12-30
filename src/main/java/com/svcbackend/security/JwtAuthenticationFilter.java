package com.svcbackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        log.info("Processing request for URI: {}", requestUri);

        // Ignorar rutas públicas
        if (requestUri.startsWith("/auth/login") || requestUri.startsWith("/auth/register")) {
            log.info("Skipping JWT filter for URI: {}", requestUri);
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            log.info("Validating token: {}", token);

            if (jwtService.tokenValidate(token)) {
                String username = jwtService.getUsername(token);
                String name = jwtService.getName(token);
                String role = jwtService.getRole(token);
                String sexo = jwtService.getSexo(token);
                log.info("Authenticated user: {} {} ({})", username, name, role, sexo);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                log.warn("Invalid token for URI: {}", requestUri);
            }
        } else {
            log.warn("No Authorization header or incorrect format for URI: {}", requestUri);
        }

        filterChain.doFilter(request, response);
    }

}
