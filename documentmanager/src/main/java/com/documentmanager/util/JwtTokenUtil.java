package com.documentmanager.util;

import com.documentmanager.config.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.*;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.lifetime}")
    private Duration lifetime;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(UserDetailsImpl principal) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = principal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        claims.put("roles", roles);
        claims.put("id", principal.getId());
        return Jwts.builder()
                .claims(claims)
                .subject(principal.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + lifetime.toMillis()))
                .signWith(getSigningKey())
                .compact();

    }

    public String getId(String token) {
        return getClaims(token).getId();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getClaims(token).get("roles", List.class);
    }

    private Claims getClaims(String token) {
         return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
