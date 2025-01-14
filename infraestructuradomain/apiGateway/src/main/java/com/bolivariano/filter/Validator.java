package com.bolivariano.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class Validator {
    public static final List<String> openEndpoints = List.of(
            "auth/sign-up",
            "auth/log-in",
            "auth/all",
            "auth/refresh",
            "/id"
    );
    Predicate<ServerHttpRequest>isSecure = serverHttpRequest -> openEndpoints.stream().noneMatch(uri->serverHttpRequest.getURI().getPath().contains(uri));
}
