/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.security;

import com.ecommerce.ecommerce.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author cdmrr
 */

@Component
public class JwtUtils {
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final int jwtExpirationsMs=180000;
    
    
    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getAddress())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpirationsMs))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
    
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch(Exception e){
            System.out.println("Error al validar el token jwt"+ e.getMessage());
            return false;
        }
    }
    
    public Claims getClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
