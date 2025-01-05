    package com.user.securityApp.controller.dto;

    import com.fasterxml.jackson.annotation.JsonPropertyOrder;

    import java.util.Set;

    @JsonPropertyOrder({"username","message","token", "roles", "status"})
    public record AuthResponse(String username,
                               String message,
                               String token,
                               Set<String> roles,
                               boolean status) {

    }
