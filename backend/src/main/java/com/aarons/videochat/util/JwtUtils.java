package com.aarons.videochat.util;

import java.security.Key;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;

public class JwtUtils {
    private final String SECRET_KEY;
    private static final Long EXPIRATION_TIME = 1000l * 60 * 60 * 24 * 7;

    public JwtUtils() {
        SecureRandom rng = new SecureRandom();
        SECRET_KEY = Long.toBinaryString(rng.nextLong());
    }

    public String generateJwtToken(Long id, String name) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .claims(claims)
                .subject(Long.toString(id))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

}
