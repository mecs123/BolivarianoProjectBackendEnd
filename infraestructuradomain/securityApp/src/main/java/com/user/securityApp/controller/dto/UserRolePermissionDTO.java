package com.user.securityApp.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;
import java.util.List;
import java.util.Set;

@JsonPropertyOrder({
        "id",
        "username",
        "fullName",
        "email",
        "dateOfBirth",
        "phone",
        "isEnabled",
        "accountNoExpired",
        "accountNoLocked",
        "roles"
})
public record UserRolePermissionDTO(
        Long id,
        String username,
        String fullName,
        String email,
        Date dateOfBirth,
        String phone,
        boolean isEnabled,
        boolean accountNoExpired,
        boolean accountNoLocked,
        Set<String> roles
) {
}