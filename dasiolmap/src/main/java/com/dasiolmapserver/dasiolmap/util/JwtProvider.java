package com.dasiolmapserver.dasiolmap.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

    private Key getStringKey() {
<<<<<<< HEAD
        System.out.println("[debug] >>> JwtProvider secret : "+secret);
=======
        System.out.println("[debug] JwtProvider secret : " + secret);
>>>>>>> feature/#4-store
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(String email) {
        System.out.println("[debug] >>> JwtProvider generateAccessToken");
        return Jwts.builder()
<<<<<<< HEAD
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .signWith(getStringKey())
                    .compact() ;
=======
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getStringKey())
                .compact();
>>>>>>> feature/#4-store
    }

    public String generateRefreshToken(String email) {
        System.out.println("[debug] >>> JwtProvider generateRefreshToken");
        return Jwts.builder()
<<<<<<< HEAD
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                    .signWith(getStringKey())
                    .compact() ;
    }


}

=======
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getStringKey())
                .compact();
    }

}
>>>>>>> feature/#4-store
