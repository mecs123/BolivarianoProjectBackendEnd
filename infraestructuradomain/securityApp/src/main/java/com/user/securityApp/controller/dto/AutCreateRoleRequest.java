package com.user.securityApp.controller.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record AutCreateRoleRequest(
        @Size(max = 3, message = "The user cannot have more then 3 roles") List<String> roleListName


) {
}
