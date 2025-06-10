package com.eparlak.personeltayintalebi.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eparlak.personeltayintalebi.service.JwtService;
import com.eparlak.personeltayintalebi.service.PersonelUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final PersonelUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService,
            PersonelUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Request URL: " + request.getRequestURL());

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        System.out.println("Auth Header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
            username = jwtService.extractUsername(jwt);
            System.out.println("Token'dan çıkarılan username: " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
              
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
               
                if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {
                    
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    System.out.println("Token geçersiz!");
                }
            } else {
                System.out.println("Username yok veya zaten authenticate olmuş");
            }

        } catch (Exception e) {
            System.out.println("JWT Filter hatası: " + e.getMessage());
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
