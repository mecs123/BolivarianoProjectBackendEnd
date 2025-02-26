package com.user.securityApp.config.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.user.securityApp.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
@Slf4j
public class JwtTokenValidator extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }



    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Obtener el token JWT del encabezado de autorización de la solicitud.
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        String path = request.getRequestURI();
        // 2. Verificar si el token no es nulo
        if(jwtToken != null) {

            // Ignorar validación JWT para rutas públicas
            if (path.startsWith("/auth/")) {
                filterChain.doFilter(request, response);  // No validar token en rutas de autenticación
                return;


            }

            // 3. El encabezado Authorization tiene el formato "Bearer <token>",
            // así que eliminamos los primeros 7 caracteres (la palabra "Bearer ")
            jwtToken = jwtToken.substring(7);

            // 4. Validar el token JWT y decodificarlo usando el método validateToken de JwtUtils.
            // Esto también verifica si el token es válido (no ha expirado, no ha sido manipulado, etc.)
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            // 5. Extraer el nombre de usuario desde el token decodificado
            String username = jwtUtils.extractUsername(decodedJWT);

            // 6. Obtener las autoridades (roles o permisos) del token.
            // El método getClanSpecific toma el token y devuelve las autoridades como un string.
            String stringAuthorties = jwtUtils.getClanSpecific(decodedJWT, "authorities").asString();

            // 7. Convertir las autoridades (una cadena separada por comas) en una lista de objetos
            // GrantedAuthority que Spring Security puede usar.
            Collection<? extends GrantedAuthority> authorities =
                    AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorties);

            // 8. Obtener el contexto de seguridad actual, que mantiene la información sobre el
            // usuario autenticado en Spring Security.
            SecurityContext context = SecurityContextHolder.getContext();

            // 9. Crear un objeto de autenticación. En este caso, estamos usando un
            // UsernamePasswordAuthenticationToken, que se usa para autenticación basada en el nombre de usuario.
            // Aquí, pasamos el nombre de usuario, pero el password es nulo (ya que estamos usando un token).
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

            // 10. Establecer el objeto de autenticación en el contexto de seguridad. Esto marca al usuario como autenticado.
            context.setAuthentication(authentication);

            // 11. Establecer el contexto actualizado en el SecurityContextHolder, para que otros componentes
            // puedan acceder al usuario autenticado.
            SecurityContextHolder.setContext(context);
        }

        // 12. Pasar la solicitud y la respuesta al siguiente filtro en la cadena de filtros.
        filterChain.doFilter(request, response);
    }

}
