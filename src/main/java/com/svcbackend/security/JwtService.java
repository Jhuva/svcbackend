package com.svcbackend.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String tokenGenerate(String username, String name, String role, String sexo) {
        SecretKey key = Keys.hmacShaKeyFor(this.SECRET_KEY.getBytes());
        return Jwts.builder()
                .subject(username)
                .claim("name", name)
                .claim("role", role)
                .claim("sexo", sexo)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public boolean tokenValidate(String token) {
        SecretKey key = Keys.hmacShaKeyFor(this.SECRET_KEY.getBytes());
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        SecretKey key = Keys.hmacShaKeyFor(this.SECRET_KEY.getBytes());
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public Claims getClaims(String token) {
        SecretKey key = Keys.hmacShaKeyFor(this.SECRET_KEY.getBytes());
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getName(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get("name");
    }

    public String getRole(String token) {
        return (String) getClaims(token).get("role");
    }

    public String getSexo(String token) {
        return (String) getClaims(token).get("sexo");
    }

}
