package com.whosricardo.gymbookingapi.service.auth.jwt;

import com.whosricardo.gymbookingapi.config.security.JwtProperties;

public class JwtSanityCheck {
    public static void main(String[] args) {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("j5hBO0lNMEsFwQ1u9iE4IXuOxiu2ghLTbtDMr0bShVUCK9j5oHtewUZACi+CIQ0wJOUvwBvBJM3EmZRto7pMFA==");
        jwtProperties.setExpirationMinutes(1);

        JwtService jwtService = new JwtServiceImpl(jwtProperties);
        String token = jwtService.generateToken("a@a.com", "MEMBER");
        System.out.println(token);
        System.out.println(jwtService.isTokenValid(token));
        System.out.println(jwtService.extractSubject(token));
        System.out.println(jwtService.extractRole(token));

        jwtProperties.setExpirationMinutes(0);
        String expiredToken = jwtService.generateToken("b@b.com", "MEMBER");
        System.out.println(expiredToken);
        System.out.println(jwtService.isTokenValid(expiredToken));
        if (jwtService.isTokenValid(expiredToken)) {
            System.out.println(jwtService.extractRole(expiredToken));
            System.out.println(jwtService.extractSubject(expiredToken));
        }
    }
}
