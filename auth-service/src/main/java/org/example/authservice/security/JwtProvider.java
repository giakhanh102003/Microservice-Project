//package org.example.authservice.security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtProvider {
//    private final String SECRET = "viet-secret-key-viet-secret-key-viet-secret-key"; // >= 32 ký tự
//    private final long EXPIRATION = 1000 * 60 * 60; // 1h
//
//    private Key getKey() {
//        return Keys.hmacShaKeyFor(SECRET.getBytes());
//    }
//
//    public String generateToken(String email, String role) {
//        Date now = new Date();
//        Date exp = new Date(now.getTime() + EXPIRATION);
//
//        return Jwts.builder()
//                .setSubject(email)
//                .claim("role", role)
//                .setIssuedAt(now)
//                .setExpiration(exp)
//                .signWith(getKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException ex) {
//            return false;
//        }
//    }
//
//    public String getEmail(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//}
