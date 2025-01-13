package com.user.securityApp.controller.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
public record AuthUpdateUserRequest(

        String username,
        String email,
        String fullName,
        Date dateOfBirth,
        String phone,
        boolean isEnabled,
        boolean accountNoExpired,
        boolean accountNoLocked,
        boolean credentialNoExpired,
        AutCreateRoleRequest roleRequest
) {
}