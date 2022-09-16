package com.example.demo.security;

import com.example.demo.exception.jwt.JWTException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    // generate token
    public String generateToken(Authentication authentication){
        String email = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        return token;
    }

    // get email from the token
    public String getEmailFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }


    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            throw new JWTException(HttpStatus.UNAUTHORIZED, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JWTException(HttpStatus.UNAUTHORIZED, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JWTException(HttpStatus.UNAUTHORIZED, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JWTException(HttpStatus.UNAUTHORIZED, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JWTException(HttpStatus.UNAUTHORIZED, "JWT claims string is empty.");
        }
    }
}
