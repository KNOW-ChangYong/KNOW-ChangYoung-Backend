package com.example.check.security;

import com.example.check.exception.InvalidTokenException;
import com.example.check.security.auth.AuthDetailService;
import com.example.check.security.auth.AuthDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${auth.jwt.secret}")
    private String secretKey;

    @Value("${auth.jwt.exp.access}")
    private Long accessTokenExpiration;

    @Value("${auth.jwt.header}")
    private String header;

    @Value("${auth.jwt.prefix}")
    private String prefix;

    private final AuthDetailService authDetailService;

    public String generateAccessToken(String id) {
        System.out.println("generate token!");
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(id)
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiration * 1000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        System.out.println(request.getHeader(header));
        String bearerToken = request.getHeader(header);
        System.out.println(bearerToken);
        if(bearerToken != null && bearerToken.startsWith(prefix)) {
            System.out.println("resolve good!");
            return bearerToken.substring(7);
        }
        System.out.println("resolve failed..");
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getSubject();
            System.out.println("validate!");
            return true;
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public String getId(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                    .getBody().getSubject();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public Authentication getAuthentication(String token) {
        AuthDetails authDetails = authDetailService.loadUserByUsername(getId(token));
        return new UsernamePasswordAuthenticationToken(authDetails, "",authDetails.getAuthorities());
    }
}
