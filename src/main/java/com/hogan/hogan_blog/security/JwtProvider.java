package com.hogan.hogan_blog.security;


import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import sun.font.TrueTypeFont;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
public class JwtProvider {
    private Key key;
    @PostConstruct
    public void init(){
        key= Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

    }
    public String generateToken(Authentication authentication){
        User principal =(User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String jwt){
        Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
        return true;
    }

    public String getUsernameFromJWT(String token) {
        Claims claims=Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
