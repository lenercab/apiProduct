package com.lenercab.authjwt.jwt;


import com.lenercab.authjwt.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Autowired
    private JwtConfig jwtConfig;

    public String generateToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining());

        return Jwts.builder().setSubject(authentication.getName())
                .claim("roles", authorities)
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret()).compact();
    }

    public Authentication getAuthentication(HttpServletRequest request){
        String token = resolveToken(request);
        if(token == null){
            return null;
        }
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
        String username = claims.getSubject();
        List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(role -> role.startsWith("ROLE_")? role:"ROLE_"+role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return username!=null ? new UsernamePasswordAuthenticationToken(username, null, authorities):null;
    }

    public boolean validateToken(HttpServletRequest request){
        String token = resolveToken(request);
        if(token == null){
            return false;
        }
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
        if(claims.getExpiration().before(new Date())){
            return false;
        }
        return true;
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(jwtConfig.getHeader());
        if(bearerToken!=null && bearerToken.startsWith(jwtConfig.getPrefix())){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}