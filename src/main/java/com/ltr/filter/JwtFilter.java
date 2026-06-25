package com.ltr.filter;

import com.ltr.exception.JwtException;
import com.ltr.model.security.UserSecurityDetails;
import com.ltr.service.security.JwtService;
import com.ltr.service.security.UserSecurityDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserSecurityDetailsService userSecurityDetailsService;

    public JwtFilter(JwtService jwtService, UserSecurityDetailsService userSecurityDetailsService) {
        this.jwtService = jwtService;
        this.userSecurityDetailsService = userSecurityDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = null;
        if (header.startsWith("Bearer ")){
            token = header.substring(7);
        }
        if (token != null && !jwtService.isExpired(token)){
            String username = jwtService.extractUsername(token);
            if (username != null && jwtService.isValid(token, username)){
                UserSecurityDetails userSecurityDetails = (UserSecurityDetails) userSecurityDetailsService.loadUserByUsername(username);
                Authentication auth = new UsernamePasswordAuthenticationToken(userSecurityDetails, null, userSecurityDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }else {
            throw new JwtException("JWT Token is expired");
        }
        filterChain.doFilter(request, response);
    }
}