package ru.veqveq.conference.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rolesList = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", rolesList);
        Date issueDate = new Date();
        Date expiredDate = new Date(issueDate.getTime() + 30 * 60 * 1000);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issueDate)
                .setExpiration(expiredDate)
                .setSubject(userDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims getBodyFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getBodyFromToken(token);
        return claimsResolver.apply(claims);
    }

    public List<String> getRoles(String token) {
        return getClaimsFromToken(
                token,
                (Function<Claims, List<String>>) claims ->
                        claims.get("roles", List.class));
    }

    public String getUsername(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }
}
