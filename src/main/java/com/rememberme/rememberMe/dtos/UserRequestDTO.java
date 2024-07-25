package com.rememberme.rememberMe.dtos;

import com.rememberme.rememberMe.domain.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

public record UserRequestDTO(

        @NotBlank(message = "NAME IS REQUIRED")
        String name,

        @NotNull(message = "EMAIL IS REQUIRED")
        @NotEmpty(message = "EMAIL IS REQUIRED")
        @NotBlank(message = "EMAIL IS REQUIRED")
        String email,

        @NotBlank(message = "PASSWORD IS REQUIRED")
        String password,

        BigDecimal balance


) {


    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(name, email, passwordEncoder.encode(password), balance);
    }
}