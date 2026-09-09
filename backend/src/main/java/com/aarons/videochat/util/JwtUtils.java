package com.aarons.videochat.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aarons.videochat.error.BadRequestException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {

    private final String SECRET_STRING;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
    }

    public JwtUtils(@Value("${JWT_KEY}") String jwtKey) {
        this.SECRET_STRING = jwtKey;
    }

    public String generateToken(Long id) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(id.toString())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getSigningKey())
                .compact();
    }

    public Claims decodeToken(String token) {
        Claims claims;

        try {
            claims = Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException err) {
            throw new BadRequestException("Session expired or invalid, did you sign in first?");
        }

        return claims;
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = decodeToken(token);
        return Long.parseLong(claims.getSubject());
    }

}
