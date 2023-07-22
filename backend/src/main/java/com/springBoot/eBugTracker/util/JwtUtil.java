package com.springBoot.eBugTracker.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final int TOKEN_VALIDITY = 3600 * 5;
    // 1. To get username from token :
    private final String SECRET_KEY = "this_is_a_secret_key";

    public String getUserNameFromToken(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // 2. To validate token and userDetails
    public boolean validateToken(UserDetails userDetails, String token) {
        String userName = getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDate(token);
        return expirationDate
                .before(new Date());
    }

    public Date getExpirationDate(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    // 3. Generate jwtToken to use in JwtService
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
