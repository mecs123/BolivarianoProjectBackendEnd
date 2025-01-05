package com.user.securityApp.controller.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record AuhthLoginRequest(@NotBlank String username,
                                @NotBlank String password

) {
}
