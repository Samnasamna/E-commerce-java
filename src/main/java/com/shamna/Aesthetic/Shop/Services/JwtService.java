package com.shamna.Aesthetic.Shop.Services;

import com.shamna.Aesthetic.Shop.Entities.Login;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;


@Service
public class JwtService {
    @Value("${jwt.secret.key}")
    private String secretKey;
    public String generateToken(Login user){
        HashMap<String,Object> claim = new HashMap<>();
        claim.put("role", user.getRole());
        return Jwts.builder()
                .claims()
                .add(claim)
                .subject(user.getUname())
                .issuer("samna")
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .and()
                .signWith(generateKey())
                .compact();
    }

    public SecretKey generateKey(){
        byte[] decode = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(decode);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction) {
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        try{
            return Jwts.parser()
                    .verifyWith(generateKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }catch(ExpiredJwtException e){
            throw new RuntimeException("Token has expired. Please login again.", e);
        }catch (JwtException e){
            throw new RuntimeException("Invalid Token",e);
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        try{
            String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isExpired(token));
        }catch (ExpiredJwtException e){
            System.out.println("The token is expired "+ e.getMessage());
            return false;
        }
    }

    private boolean isExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token,Claims::getExpiration);
    }
}
