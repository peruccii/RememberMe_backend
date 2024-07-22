package com.rememberme.rememberMe.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/**
 * Class {@code validPasswordDTO} represent the data transfer object of the password validation.
 * <p>
 *  It contain dto`s attributes and your respective types.
 * </p>
 *
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

public record ValidPasswordDTO(
        @Valid

        @NotBlank(message = "PASSWORD IS REQUIRED")
        String password
) {
}
