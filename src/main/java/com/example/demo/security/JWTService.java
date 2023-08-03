package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {
    public String extractUserName(String token) {
    return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    final static String KEYSECU = "556A586E327235753878214125442A472D4B6150645367566B59703373367639";
private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
}
public String generateToken(UserDetails userDetails){
    return generateToken(new HashMap<>(),userDetails);
}

    public String generateToken(Map<String,Object> extractClaims
            , UserDetails userDetails){
    return Jwts.builder().setClaims(extractClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*24))
            .signWith(getSignkey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getSignkey() {
    byte[] keyBytes = Decoders.BASE64.decode(KEYSECU);
    return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
    final String username = extractUserName(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
    return extractClaim(token,Claims::getExpiration);
    }

}
