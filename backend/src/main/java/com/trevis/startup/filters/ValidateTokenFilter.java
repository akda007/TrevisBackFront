package com.trevis.startup.filters;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.trevis.startup.interfaces.JwtTokenManager;
import com.trevis.startup.sessions.UserSession;

import java.io.IOException;

@Component
public class ValidateTokenFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenManager jwtTokenManager;

    @Autowired
    UserSession userSession;

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if(token != null) {
            try {
                token = token.replace("Bearer ", "");
                Claims claims = jwtTokenManager.extractClaims(token);

                userSession.setId(Long.parseLong(claims.getId()));
                userSession.setAdmin(claims.get("admin", Boolean.class));
            }
            catch (RuntimeException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            }
        }

        filterChain.doFilter(request, response);
    }
}
