package pe.edu.upc.moneyproject.securities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000;

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        // Convertir tu SECRET en una clave válida para HS512
        signingKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // ============================================
    // EXTRAER CLAIMS
    // ============================================
    private Claims getAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsernameFromToken(String token) {
        return getAllClaims(token).getSubject();
    }

    public Date getExpiration(String token) {
        return getAllClaims(token).getExpiration();
    }

    private boolean isExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    // ============================================
    // GENERAR TOKEN
    // ============================================
    public String generateToken(UserDetails userDetails, int idUsuario) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("roles", userDetails.getAuthorities());
        claims.put("id", idUsuario);  // NECESARIO para permisos por ID

        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(signingKey, Jwts.SIG.HS512)   // 👈 firma correcta con JJWT 0.13.0
                .compact();
    }

    // ============================================
    // VALIDAR TOKEN
    // ============================================
    public boolean validateToken(String token, UserDetails userDetails) {
        try {
            final String username = getUsernameFromToken(token);
            return username.equals(userDetails.getUsername()) && !isExpired(token);
        } catch (SignatureException e) {
            return false;
        }
    }

    // ============================================
    // EXTRA: OBTENER ID desde el token
    // ============================================
    public Integer getIdFromToken(String token) {
        return getAllClaims(token).get("id", Integer.class);
    }
}
