package com.user.securityApp.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
public record AuthCreateUserRequest(
        @NotBlank(message = "Username cannot be empty")
        String username,

        @NotBlank(message = "Password cannot be empty")
        String password,
        String email,
        String fullName,
        Date dateOfBirth,
        String phone,

        boolean isEnabled,

        boolean accountNoExpired,

        boolean accountNoLocked,

        boolean credentialNoExpired,

        @Valid
        AutCreateRoleRequest roleRequest
) {
}