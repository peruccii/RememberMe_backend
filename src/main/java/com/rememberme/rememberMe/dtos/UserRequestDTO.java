package com.rememberme.rememberMe.dtos;

import com.rememberme.rememberMe.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

public record UserRequestDTO(

        String name,

        String email,

        String password,

        BigDecimal balance


) {

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(name, email, passwordEncoder.encode(password), balance);
    }
}