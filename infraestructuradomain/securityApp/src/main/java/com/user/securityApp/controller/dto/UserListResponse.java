package com.user.securityApp.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;
import java.util.List;

@JsonPropertyOrder(
        {"username",
                "password" ,
                "fullName" ,
                "email",
                "dateOfBirth",
                "phone",
                "isEnabled",
                "accountNoExpired",
                "accountNoLocked",
                "roles"})
public record UserListResponse(
        String username,
        String password,
        String fullName,
        Date dateOfBirth,
        String email,
        String phone,
        boolean isEnabled,
        boolean accountNoExpired,
        boolean accountNoLocked,
        boolean credentialNoExpired, // Agregado aquí
        List<String> roles


) {
}
