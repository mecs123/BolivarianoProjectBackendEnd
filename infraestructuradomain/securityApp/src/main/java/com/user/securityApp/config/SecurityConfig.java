package com.user.securityApp.config;

import com.user.securityApp.config.filter.JwtTokenValidator;
import com.user.securityApp.service.UserDetailServiceImpl;
import com.user.securityApp.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // Configurar autorización de rutas
                .authorizeRequests(authorize -> {
                    // Endpoints públicos
                    authorize.requestMatchers(HttpMethod.DELETE,"/auth/delete").permitAll();
                    authorize.requestMatchers(HttpMethod.OPTIONS,"/**").permitAll();

                    authorize.requestMatchers(HttpMethod.POST,"/auth").permitAll();

                    authorize.requestMatchers(
                            "/auth/sign-up",
                            "auth/refresh",
                            "/auth/log-in",
                            "/auth/all",
                            "/auth/edit",
                            "/auth/delete",
                            "/auth/byId",
                            "/auth/roles",
                            "/actuator/**"
                    ).permitAll();
                    authorize.requestMatchers(
                            new AntPathRequestMatcher("/swagger-ui/**"),
                            new AntPathRequestMatcher("/v3/api-docs/**")
                    ).permitAll();

                    authorize.requestMatchers(HttpMethod.POST, "/method/post").hasAnyRole("ADMIN", "DEVELOPER");
                    authorize.requestMatchers(HttpMethod.GET, "/teacher/all").hasAnyRole("ADMIN", "DEVELOPER");

                    authorize.requestMatchers(HttpMethod.PATCH, "/method/patch").hasAnyAuthority("REFACTOR");

                    // Permitir solicitudes locales (para pruebas en localhost)
                    authorize.requestMatchers(request -> {
                        String remoteHost = request.getRemoteHost();
                        return "localhost".equals(remoteHost) ||
                                "127.0.0.1".equals(remoteHost) ||
                                "0:0:0:0:0:0:0:1".equals(remoteHost);
                    }).permitAll();

                    // Bloquear cualquier solicitud no configurada
                    authorize.anyRequest().denyAll();
                })

                // Configurar CORS para Angular
                .cors(Customizer.withDefaults())

                // Configurar CSRF con soporte para Angular
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                new AntPathRequestMatcher("/swagger-ui/**"),
                                new AntPathRequestMatcher("/v3/api-docs/**"),
                                request -> {
                                    String remoteHost = request.getRemoteHost();
                                    return "localhost".equals(remoteHost) ||
                                            "127.0.0.1".equals(remoteHost) ||
                                            "0:0:0:0:0:0:0:1".equals(remoteHost);
                                }
                        )
                )

                // Configurar manejo de sesiones
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Agregar filtro de validación JWT
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)

                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Dominio del frontend
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type", "X-XSRF-TOKEN"));
        configuration.setExposedHeaders(List.of("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }








    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailServiceImpl userDetailService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
