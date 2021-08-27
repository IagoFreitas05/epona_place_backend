package main.place.securityjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import main.place.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    @Value("${security.jwt.expiration}")
    private String expiration;
    @Value("${security.jwt.signature-key}")
    private String signatureKey;

    public String generateToken(User user){
        long expString = Long.valueOf(expiration);
        LocalDateTime dateTimeExpiration = LocalDateTime.now().plusMinutes(expString);
        Date data = Date.from(dateTimeExpiration.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts
                    .builder()
                    .setSubject(user.getMail())
                    .setExpiration(data)
                    .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                    .parser()
                    .setSigningKey(signatureKey)
                    .parseClaimsJws(token)
                    .getBody();
    }

    public boolean isValidToken(String token){
        try{
            Claims claims =  getClaims(token);
            Date expiration = claims.getExpiration();
            LocalDateTime localDateTime =  expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);
        }catch (Exception e){
            return false;
        }
    }

    public String getLoggedUser(String token){
        return (String) getClaims(token).getSubject();
    }
}
