package com.eparlak.personeltayintalebi.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    // Secret key - propertiesten alınacak sonra.
    private final String SECRET_KEY = "mySecretKey123456789012345678901234567890123456789012345678901234567890";
    private final long JWT_EXPIRATION = 86400000; // 24 saat (milisaniye)
    
    // Token üret
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)                                          // Token'ın sahibi
                .issuedAt(new Date())                                      // Oluşturulma zamanı
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION)) // Bitiş zamanı
                .signWith(getSigningKey())                                 // İmzala
                .compact();                                                // String'e çevir
    }
    
    // Token'dan username çıkar
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    // Token geçerli mi kontrol et
    public boolean isTokenValid(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username)) && !isTokenExpired(token);
    }
    
    // Token süresi dolmuş mu
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    // Token'dan expiration date çıkar
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    // Token'dan belirli bir claim çıkar (generic method)
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    // Token'ı parse et ve tüm claims'leri al
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())                               // İmzayı doğrula
                .build()
                .parseSignedClaims(token)                                  // Token'ı parse et
                .getPayload();                                             // Claims'leri al
    }
    
    // Secret key'i SecretKey objesine çevir
    private SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    
    public void debugToken(String token) {
        try {
            Claims claims = extractAllClaims(token);
            
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Issued At: " + claims.getIssuedAt());
            System.out.println("Expiration: " + claims.getExpiration());
            System.out.println("Is Expired: " + isTokenExpired(token));
        } catch (Exception e) {
            System.out.println("Token parse hatası: " + e.getMessage());
        }
    }
}