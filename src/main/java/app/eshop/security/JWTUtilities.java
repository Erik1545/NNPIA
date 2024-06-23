package app.eshop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import java.util.Date;

@Component
public class JWTUtilities {

    private String jwtKey = "BH13WrDvUbDRinMxeUMZRlNgBc1R4pj7/HQMu/LzSk9pk9MLe6322c8Wq0JAFNE2LLOfHpOHgPnutu94Q176o9bjkgnBeZzxYvDHrlDrOMluyuVmvLEohqMTgvwqfx9kHtMVaT7hZZd22lvzmZKfP5ogtO0orp9v0c7ZCS8IyDiQ4fkLNdY16D1wBOi9EBEShzTBLAgcmE2dxMFzpZOoiFzQcKJI2NVen+UsaS4ffgzjld4Mb6WrLJA1eaMBjqY0gR0w/HJju2YfZ16RWicvf/ySzKAoxo6Hc05B8ARo78QZFh6lhyE52t0VOD4SqpU0EGNq54v7wnnSwY2Fuk4O";
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){return extractClaim(token, Claims::getExpiration);}

    public boolean hasClaim(String token, String claimName){
        final Claims claims = extractAllClaims(token);
        return claims.get(claimName) != null;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims (String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey))).build().parseSignedClaims(token).getPayload();
    }
    private Boolean isTokenExpired(String token){return extractExpiration(token).before(new Date());}

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails);
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> claims){return createToken(claims, userDetails);}

    private String createToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts.builder().claims(claims)
                .subject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey))).compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
