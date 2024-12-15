/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.WebAuthnDsl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author cdmrr
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtUtils jwUtils;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        //obtener el toke JWT del encabezado de la utorizacion
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        
        String jwtToken =authHeader.substring(7);
        
        try {
            //Validat el token y extraer el correo del usuatio
            Claims claims = jwUtils.getClaims(jwtToken);
            String userEmail = claims.getSubject();
            
            if(userEmail != null && SecurityContextHolder.getContext().getAuthentication()==null){
                //Cargar los detalles del usuario desde la base de datos
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                
                if(jwUtils.validateToken(jwtToken)){
                    
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }catch(Exception e){
            System.out.println("Error al validar el token"+e.getMessage());
        }
        
        // Continuar cadena de filtros
        filterChain.doFilter(request, response);
        
    }
}
