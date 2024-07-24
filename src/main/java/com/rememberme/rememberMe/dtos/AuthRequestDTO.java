package com.rememberme.rememberMe.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @Valid

        @NotBlank(message = "EMAIL IS REQUIRED")
        String email,

        @NotBlank(message = "PASSWORD IS REQUIRED")
        String password) {
}
