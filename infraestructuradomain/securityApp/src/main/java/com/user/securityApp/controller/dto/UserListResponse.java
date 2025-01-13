package com.user.securityApp.controller.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Date;
import java.util.List;

@JsonPropertyOrder(
        {
                "id",
                "username",
                "fullName" ,
                "email",
                "dateOfBirth",
                "phone",
                "isEnabled",
                "accountNoExpired",
                "accountNoLocked",
                "roles"})
public record UserListResponse(
        Long id,
        String username,
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
