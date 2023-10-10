package com.debugenadoideas.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Alberto Puche Algarin
 * @version 1.0
 * @description JWTService
 * @date
 */
@Service
public class JWTService {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    public static final String JWT_SECRET= "jxgEQe.XHuPq8VdbyYFNkAN.dudQ0903YUn4";

    // Obtiene los Claims(payload) del token. Decodificar la clave
    private Claims getAllClaimsFromToken(String token) {
        final var key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Metodo generico, para recibir los Claims
    public <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final var claims = this.getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Tiempo de expiracion del token
    private Date getExpirationDateFromToken(String token) {
        return this.getClaimsFromToken(token, Claims::getExpiration);
    }

    // Verifica si el token esta expirado
    private Boolean isTokenExpired(String token) {
        final var expirationDate = this.getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    // Retorna el usuario del token
    public String getUsernameFromToken(String token) {
        return this.getClaimsFromToken(token, Claims::getSubject);
    }

    // Valida que el token sea real
    public  Boolean validateToken(String token, UserDetails userDetails) {
        final var usernameFromUserDetails  = userDetails.getUsername();
        final var usernameFromJWT  = this.getUsernameFromToken(token);

        return (usernameFromUserDetails.equals(usernameFromJWT)) && !this.isTokenExpired(token);
    }

    // Obtiene el token
    private String getToken(Map<String, Object> claims, String subject) {
        final var key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(key)
                .compact();
    }

    // Genera el token
    public String generateToken(UserDetails userDetails) {
        final Map<String, Object> claims = Collections.singletonMap("ROLES", userDetails.getAuthorities().toString());
        return this.getToken(claims, userDetails.getUsername());
    }

}
