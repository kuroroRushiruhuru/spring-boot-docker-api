package com.example.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 秘密鍵
    private final Key SECRET_KEY =
            Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // JWT生成
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(SECRET_KEY)
                .compact();
    }

    // JWTからユーザー名取得
    public String extractUsername(String token) {

        return getClaims(token).getSubject();
    }

    // JWT検証
    public boolean validateToken(String token) {

        try {
            getClaims(token);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // Claims取得
    private Claims getClaims(String token) {

        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}