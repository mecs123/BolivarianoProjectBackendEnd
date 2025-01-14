    package com.user.securityApp.controller.dto;

    import com.fasterxml.jackson.annotation.JsonPropertyOrder;

    import java.util.Set;

    @JsonPropertyOrder({"username","message","token", "refreshToken", "roles", "status"})
    public record AuthResponse(String username,
                               String message,
                               String token,
                               String refreshToken,
                               Set<String> roles,
                               boolean status
    ) {

    }
