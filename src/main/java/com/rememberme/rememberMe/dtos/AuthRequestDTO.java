package com.rememberme.rememberMe.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for user authentication requests.
 * <p>
 * This record encapsulates the credentials required for authenticating a user.
 * </p>
 *
 * @param email    the email of the user (must not be blank)
 * @param password the password of the user (must not be blank)
 * @code Data Transfer Object (DTO) for user authentication requests.
 */
public record AuthRequestDTO(
        @Valid

        @NotBlank(message = "EMAIL IS REQUIRED")
        String email,

        @NotBlank(message = "PASSWORD IS REQUIRED")
        String password) {
}