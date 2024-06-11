package com.ms.pruebatecnica.service.impl;

import com.ms.pruebatecnica.exception.ExceptionPruebaTecnica;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ms.pruebatecnica.util.Constantes.*;

@Service
public class TokenService {

    private static final String SECRET_KEY = "ins4scXcgy8GcsNRtm9ShZyyXvzfNu0qSygMgxtRCt+uzPXfifZQALR8/6YeWEIXKSwUHGxeHsge6NA6hV06zA==";

    public String generateToken(String token) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", "user");
        return createToken(claims, token);
    }

    private String createToken(Map<String, Object> claims, String token) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(token)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String validateToken(String token) {
        try {
            if (!isTokenExpired(token)) {
                Claims claims = extractAllClaims(token);
                Date expirationDate = claims.getExpiration();
                long remainingTime = expirationDate.getTime() - System.currentTimeMillis();
                long remainingSeconds = remainingTime / 1000;
                return String.format(TOKEN_ACTIVE_MESSAGE, remainingSeconds);
            } else {
                throw new ExceptionPruebaTecnica(COD03, HttpStatus.UNAUTHORIZED, TOKEN_EXPIRED);
            }
        } catch (ExpiredJwtException ex) {
            throw new ExceptionPruebaTecnica(COD03, HttpStatus.UNAUTHORIZED, TOKEN_EXPIRED);
        }
    }

    public void validateTokenExpired(String token) {
        try{
            if (isTokenExpired(token)) {
                throw new ExceptionPruebaTecnica(COD03, HttpStatus.UNAUTHORIZED, TOKEN_EXPIRED);
            }
        }catch (ExpiredJwtException ex){
            throw new ExceptionPruebaTecnica(COD03, HttpStatus.UNAUTHORIZED, TOKEN_EXPIRED);
        }
    }
}
