package com.bolivariano.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    public static final String SECRET ="1231231312313131313131313131313131313131313123424242424242342242";

    public void validateTokenEasy(String token){
        Jwts.parser()

                .setSigningKey(getSignKey())
                .parseClaimsJws(token);

    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Método para validar el token JWT
    public DecodedJWT validateToken(String token) {
        try {
            // Usar el mismo algoritmo HMAC256 para validar el token
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

            // Crear un verificador que valida el token usando el algoritmo y el emisor
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator) // El emisor debe coincidir con el esperado
                    .build();

            // Verifica el token y devuelve el token decodificado si es válido
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;

        } catch (JWTVerificationException exception) {
            // Si el token no es válido, lanza una excepción con un mensaje
            throw new JWTVerificationException("Token no es valido, No autorizado");
        }
    }




}
