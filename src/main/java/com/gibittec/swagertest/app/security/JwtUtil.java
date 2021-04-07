package com.gibittec.swagertest.app.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

public class JwtUtil {
    static void addAuthentication(HttpServletResponse res, String username){
        String token = Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, "P@tit0") //hash para firmar la clave
                .compact();

        res.addHeader("Authorization", "Bearer " + token); //agregamos el token al header
    }

    public static Authentication getAuthentication(HttpServletRequest servletRequest) {
        String token = servletRequest.getHeader("Authorization"); //obtenemos el token que viene en el encabezado

        if(token != null){
            String user = Jwts.parser()
                    .setSigningKey("P@tit0")
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody()
                    .getSubject();

            return user != null? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()):null;
        }
        return null;
    }
}
