package com.eztraining.userservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoleCheckRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("checking roles...");
        String roleHeader = request.getHeader("Roles");
        System.out.println(request);
        if (roleHeader != null) {
            List<GrantedAuthority> authorities = Arrays.stream(roleHeader.split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            System.out.println(authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(authentication);
        }
        // Forward the request to the next filter or controller
        filterChain.doFilter(request, response);
    }
}
