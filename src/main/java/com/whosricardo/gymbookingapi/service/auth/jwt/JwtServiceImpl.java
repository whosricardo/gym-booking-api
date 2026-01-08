package com.whosricardo.gymbookingapi.service.auth.jwt;

import com.whosricardo.gymbookingapi.config.security.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private static final String ROLE_CLAIM = "role";

    private final JwtProperties jwtProperties;

    public JwtServiceImpl(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public String generateToken(String subject, String role) {
        Instant now = Instant.now();
        Instant expiresAt = now.plusSeconds(jwtProperties.getExpirationMinutes() * 60);

        return Jwts.builder()
                .subject(subject)                       // sub
                .claim(ROLE_CLAIM, role)               // role
                .issuedAt(Date.from(now))              // iat
                .expiration(Date.from(expiresAt))      // exp
                .signWith(getSigningKey())             // HS256 inferred by key
                .compact();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            parseClaims(token); // valida assinatura + expiração
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    @Override
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get(ROLE_CLAIM, String.class));
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = parseClaims(token).getPayload();
        return resolver.apply(claims);
    }

    private Jws<Claims> parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
    }

    private SecretKey getSigningKey() {
        String secret = jwtProperties.getSecret();
        if (secret == null || secret.isBlank()) {
            throw new IllegalStateException("JWT secret is not configured (security.jwt.secret).");
        }
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
