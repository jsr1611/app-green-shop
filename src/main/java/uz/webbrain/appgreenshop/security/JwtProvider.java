package uz.webbrain.appgreenshop.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.webbrain.appgreenshop.entity.Role;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider{
    private static Long EXPIRATION_TIME = 5 * 60 * 60 * 1000L;
    private static String KEY = "ThisKeySecretKeyForJWT";

    public String generateToken(String username, Set<Role> roles){

        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, KEY)
                .claim("roles", roles)
                .compact();
        return token;
    }
}
