package ua.ithillel.tripplanner.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.ithillel.tripplanner.model.security.HillelUserDetails;

import java.util.Date;

@Component
public class JwtUtil {
    private static long TOKEN_VALIDITY = 24 * 60 * 60 * 1000; // ms

    @Value("${jwt.secret}")
    private String secret;

    public Claims getClaims(String jwt) {
        JwtParser parser = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build();

        return parser.parseSignedClaims(jwt).getPayload();
    }

    public String generateToken(HillelUserDetails userDetails) {
        return Jwts.builder()
                .id(userDetails.getId() + "")
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(TOKEN_VALIDITY + System.currentTimeMillis()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}
