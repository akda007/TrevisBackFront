package com.trevis.startup.interfaces;

import io.jsonwebtoken.Claims;

import java.util.Map;

import com.trevis.startup.exceptions.UnauthorizedException;

public interface JwtTokenManager {
    Claims extractClaims(String token) throws UnauthorizedException;
    boolean isTokenValid(String token);
    String buildToken(Map<String, Object> claims, String subject, Long userId);
}
