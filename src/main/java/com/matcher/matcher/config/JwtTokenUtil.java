package com.matcher.matcher.config;

import com.matcher.matcher.entity.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    public static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secretKey;

    //generate token for user
    public String generateToken(Account account) {
//        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setSubject(String.format("%s,%s",account.getId(), account.getUsername()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

    }
}
