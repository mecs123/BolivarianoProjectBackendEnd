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

        @NotBlank(message = "Full name cannot be empty")
        String fullName,

        @Past(message = "Date of birth must be in the past")
        Date dateOfBirth,

        @NotBlank(message = "Phone number cannot be empty")
        String phone,

        @NotNull(message = "Enable status cannot be null")
        boolean isEnabled,

        @NotNull(message = "Account expiration status cannot be null")
        boolean accountNoExpired,

        @NotNull(message = "Account lock status cannot be null")
        boolean accountNoLocked,

        @NotNull(message = "Credential expiration status cannot be null")
        boolean credentialNoExpired,

        @Valid
        AutCreateRoleRequest roleRequest
) {
}