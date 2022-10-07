package com.matcher.matcher.config;

import com.matcher.matcher.entity.account.Account;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {
    public static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secretKey;

     public String generateToken(Account account) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s",account.getId(), account.getUsername()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();

     }

//     private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(JwtTokenUtil.class);

     public boolean validateToken(String token) {
         try {
             Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
             return true;
         } catch (ExpiredJwtException ex) {
             System.err.println("JWT expired" + ex.getMessage());
//             LOGGER.log(Level.parse("JWT expired"), ex.getMessage());
         } catch (IllegalArgumentException ex) {
             System.err.println("Token is null, empty, or only whitespace" + ex.getMessage());
//             LOGGER.log(Level.parse("Token is null, empty, or only whitespace"), ex.getMessage());
         } catch (MalformedJwtException ex) {
             System.err.println("JWT is invalid" + ex.getMessage());
//             LOGGER.log(Level.parse("JWT is invalid"), ex.getMessage());
         } catch (UnsupportedJwtException ex) {
             System.err.println("JWT is not supported" + ex.getMessage());
//             LOGGER.log(Level.parse("JWT is not supported"), ex.getMessage());
         } catch (SignatureException ex) {
             System.out.println("Signature validation failed");
//             LOGGER.info("Signature validation failed");
         }
         System.out.println("test");
         return false;
     }

     private Claims parseClaims(String token) {
         return Jwts.parser()
                 .setSigningKey(secretKey)
                 .parseClaimsJws(token)
                 .getBody();
     }

     public String getSubject(String token) {
         return parseClaims(token).getSubject();
     }

}
