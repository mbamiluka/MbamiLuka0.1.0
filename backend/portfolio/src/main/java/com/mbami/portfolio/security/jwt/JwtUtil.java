package com.mbami.portfolio.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

@Component
public class JwtUtil {

    private static final Logger logger = Logger.getLogger(JwtUtil.class.getName());

    // Replace this with a secure key in a real application, ideally fetched from environment variables
    public static final String SECRET = System.getenv("MBAMI_PORTFOLIO_JWT_SECRET");

    // Generate token with given user name
    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        String token = createToken(claims, userName);
        logger.info("Token generated: " + token);
        return token;
    }

    // Create a JWT token with specified claims and subject (user name)
    private String createToken(Map<String, Object> claims, String userName) {
        try {
            logger.info("Creating token with claims: " + claims + " and userName: " + userName);
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userName)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60*3)) // Token valid for 60 minutes
                    .signWith(getSignKey(), SignatureAlgorithm.HS256)
                    .compact();
            logger.info("Token created successfully: " + token);
            return token;
        } catch (Exception e) {
            logger.severe("Error creating JWT token: " + e.getMessage());
            throw e;
        }
    }

    // Get the signing key for JWT token
    private Key getSignKey() {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET);
            Key key = Keys.hmacShaKeyFor(keyBytes);
            return key;
        } catch (Exception e) {
            logger.severe("Error getting signing key: " + e.getMessage());
            throw e;
        }
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        try {
            String username = extractClaim(token, Claims::getSubject);
            return username;
        } catch (Exception e) {
            logger.severe("Error extracting username from token: " + e.getMessage());
            throw e;
        }
    }

    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        try {
            logger.info("Extracting expiration date from token");
            Date expiration = extractClaim(token, Claims::getExpiration);
            logger.info("Expiration date extracted: " + expiration);
            return expiration;
        } catch (Exception e) {
            logger.severe("Error extracting expiration from token: " + e.getMessage());
            throw e;
        }
    }

    // Extract a claim from the token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            logger.info("Extracting claim from token");
            final Claims claims = extractAllClaims(token);
            T claim = claimsResolver.apply(claims);
            logger.info("Claim extracted: " + claim);
            return claim;
        } catch (Exception e) {
            logger.severe("Error extracting claim from token: " + e.getMessage());
            throw e;
        }
    }

    // Extract all claims from the token
    private Claims extractAllClaims(String token) {
        try {
            logger.info("Extracting all claims from token");
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            logger.info("All claims extracted successfully");
            return claims;
        } catch (Exception e) {
            logger.severe("Error extracting all claims from token: " + e.getMessage());
            throw e;
        }
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        try {
            Boolean isExpired = extractExpiration(token).before(new Date());
            logger.info("Token expired: " + isExpired);
            return isExpired;
        } catch (Exception e) {
            logger.severe("Error checking if token is expired: " + e.getMessage());
            throw e;
        }
    }

    // Validate the token against user details and expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = extractUsername(token);
            Boolean isValid = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
            logger.info("Token valid: " + isValid);
            return isValid;
        } catch (Exception e) {
            logger.severe("Error validating token: " + e.getMessage());
            throw e;
        }
    }
}