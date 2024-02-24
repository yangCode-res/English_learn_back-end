package com.yang.english.Security;

import com.yang.english.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService; // 假设这是你的自定义Token服务

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {

        String token = extractToken(request);
        if (token != null && tokenService.verifyToken(token)) {
            setAuthenticationContext(token, request);
        }

        chain.doFilter(request, response);
    }

    private void setAuthenticationContext(String token, HttpServletRequest request) {
        String username = tokenService.getUsernameFromToken(token);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                username, null, Collections.singletonList(new SimpleGrantedAuthority("USER")));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("admin-token");
        if (bearerToken != null ) {
            return bearerToken;
        }
        return null;
    }
}
