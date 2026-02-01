package com.LearnSpringBoot.InternalWorkingSpringBoot.Security;

import com.LearnSpringBoot.InternalWorkingSpringBoot.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class AuthUtill {

    @Value("${jwt.secretKey}")
    private String secretKey;

    //This is the Header
    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    //Generate The JWT Token
    public String generateAccessToken(User user) {//This User means PayLoad
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("UserId",user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 + 10))
                .signWith(getSecretKey())
                .compact();
    }

    //get Username from token
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.getSubject();
    }



}
