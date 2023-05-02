package com.assigment.Holabus.Utils;

import com.assigment.Holabus.Model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret-key}")
    private String jwtSecret;

    @Value("${jwt.expiration-time}")
    private int jwtExpirationTime;

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpirationTime);
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .claim("role", userPrincipal.getRole().getAuthority())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getSigningKey())
                .compact();
    }

    public String getEmailFromJwtToken(String jwtToken) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody()
                .getSubject();
    }

    public boolean validateAccessToken(String token) {
        try {
            Jwts
                    .parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("JWT token expired - {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT not supported - {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("JWT not recognizable - {}", e.getMessage());
        } catch (SignatureException e) {
            logger.error("JWT signature not matched - {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("There is no token to check - {}", e.getMessage());
        }
        return false;
    }
}
