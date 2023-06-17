package com.project.studentmgtsystemproject.security.service;


import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    /**
     * Quick overview for JWT
     * 1- generate JWT token
     * 2- generate token from username
     * 3- get username from token
     * 4- validate JWT token
      */


    /**
     * Important
     * logging feature can be  should be implemented in every method
     * that we need to get more/ detailed information.
     */

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    @Value("{backendapi.app.jwtExpirationsMs}")
    private long jwtExpirationMs;

    @Value("{backendapi.app.jwtSecret}")
    private String jwtSecret;

    public String generateJtwToken(Authentication authentication){
        // get infos of logged-in user from context
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication. getPrincipal();
        return generateTokenFromUsername(userDetails.getUsername());
    }
    public boolean validateJwtToken(String jwtToken){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken);
            return true;
        } catch (ExpiredJwtException e){
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        }catch(UnsupportedJwtException e){
            LOGGER.error("JWT token is unsupported: {}", e.getMessage());

        }catch (MalformedJwtException e){
            LOGGER.error("JWT token is invalid: {}", e.getMessage());

        }catch (SignatureException e){
            LOGGER.error("Jwt Signature is invalid: {}", e.getMessage());

        }catch (IllegalArgumentException e){
            LOGGER.error("JWT is empty: {} ", e.getMessage());
        }
        return false;
    }

    public String generateTokenFromUsername(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.ES512, jwtSecret)
                .compact();
    }
    public String getUserNameFromJwtToken(String token){
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

}
