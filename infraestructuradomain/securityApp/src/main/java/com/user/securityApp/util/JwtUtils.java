package com.user.securityApp.util;

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

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;


    @Value("${security.jwt.key.private.expiration}")
    private long jwtExpiration;

    @Value("${security.jwt.user.refresh-token.expiration}")
    private  long refreshExpiration;

    // Método para crear un JWT basado en la autenticación del usuario
    public String createToken(Authentication authentication, String username) {


        // Crear el algoritmo HMAC256 usando la clave privada para firmar el token
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        Authentication getUserNameAuthenticaPrincipal = SecurityContextHolder.getContext().getAuthentication();
        // Obtener las autoridades (roles/permisos) del usuario y convertirlas a una cadena separada por comas
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority) // Extrae el nombre de cada autoridad
                .collect(Collectors.joining(","));  // Las concatena con comas, como "read,write,create,delete"

        // Crear el token JWT con varios parámetros
        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator) // Establece el emisor (usualmente el nombre del servicio o la aplicación)
                .withSubject(getUserNameAuthenticaPrincipal.getPrincipal().toString())          // El sujeto es el nombre de usuario
                .withClaim("authorities", authorities) // Agrega el claim "authorities" con los roles
                .withIssuedAt(new Date())      // Establece la fecha y hora de emisión del token
                .withExpiresAt(new Date(System.currentTimeMillis() + this.jwtExpiration)) // Establece la fecha de expiración (30 minutos)
                .withJWTId(UUID.randomUUID().toString()) // Establece un ID único para el JWT
                .withNotBefore(new Date(System.currentTimeMillis())) // Establece la fecha de inicio de validez (en este caso es inmediatamente)
                .sign(algorithm);  // Firma el token con el algoritmo HMAC256 usando la clave privada

        // Devuelve el token JWT generado
        return jwtToken;
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

    // Método para generar un Refresh Token
    public String generateRefreshToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        String refreshToken = JWT.create()
                .withIssuer(this.userGenerator)  // Emisor del token
                .withSubject(username)           // Usuario asociado
                .withIssuedAt(new Date())        // Fecha de emisión
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshExpiration)) // Expiración
                .withJWTId(UUID.randomUUID().toString())  // ID único del token
                .sign(algorithm);               // Firma del token

        return refreshToken;
    }

    // Método para extraer el nombre de usuario desde el token decodificado
    public String extractUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject().toString();  // El nombre de usuario es el "subject" del token
    }

    // Método para obtener un claim específico del token decodificado
    public Claim getClanSpecific(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);  // Devuelve el valor del claim solicitado por su nombre
    }

    // Método para obtener todos los claims del token decodificado
    public Map<String, Claim> returnAllClain(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();  // Devuelve todos los claims del token
    }

}
