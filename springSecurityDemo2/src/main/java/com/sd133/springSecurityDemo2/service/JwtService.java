package com.sd133.springSecurityDemo2.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private final String secretKey;

    public JwtService(@Value("${jwt.secret}") String secretKey){
        this.secretKey = secretKey;
    }

    public String extractUsername(String token) {
        return extractClaims(token,Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        Claims claims =  Jwts
                        .parser()
                            .verifyWith(getKey())
                        .build()
                            .parseSignedClaims(token)
                            .getPayload();

        return claims;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractTokenExpiration(token).before(new Date());
    }

    private Date extractTokenExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }


    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();

        String token = Jwts
                .builder()
                .header()
                    .add("typ","JWT")
                .and()
                    .claims()
                    .add(claims)
                    .subject(username)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                .and()
                    .signWith(getKey())
                    .compact();

        return token;
    }

    public SecretKey getKey(){
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
