package design.car.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    static final long EXPIRATION_TIME = 86_400_000; // Must be shorter in production.
    static final String PREFIX = "Bearer";

    // for the testing only.
    static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // to generate signed JWT token
    public String getToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // Get a token from request Authorization header, verify token, and return associated user
    public String getAuthUser(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (token != null) {
            return Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token.replace(PREFIX, ""))
                    .getBody().getSubject();
        }
        return null;
    }
}
