package com.bolivariano.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;


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
