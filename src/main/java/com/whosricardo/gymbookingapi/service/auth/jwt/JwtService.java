package com.whosricardo.gymbookingapi.service.auth.jwt;

public interface JwtService {
    String generateToken(String subject, String role);
    boolean isTokenValid(String token);
    String extractSubject(String token);
    String extractRole(String token);
}
