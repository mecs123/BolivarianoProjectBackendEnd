package com.user.securityApp.controller.dto;

import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AuthEmailResponse(
       String mensaje,
       String email,
       String fullName,
       String usuario


) {
}
