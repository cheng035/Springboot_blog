package com.hogan.hogan_blog.security;

import com.hogan.hogan_blog.model.User;
import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
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
                .setSubject(principal.getUserName())
                .signWith(key)
                .compact();
    }
}
