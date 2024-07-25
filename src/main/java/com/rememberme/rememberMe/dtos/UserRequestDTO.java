package com.rememberme.rememberMe.dtos;

import com.rememberme.rememberMe.domain.User;

import java.math.BigDecimal;

public record UserRequestDTO(

        String name,

        String email,

        String password,

        BigDecimal balance


) {

    public User toUser() {
        return new User(name, email, password, balance);
    }
}