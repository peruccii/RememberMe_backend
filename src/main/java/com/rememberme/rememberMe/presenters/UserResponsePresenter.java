package com.rememberme.rememberMe.presenters;

import java.util.UUID;

public record UserResponsePresenter(
        UUID id,

        String name,

        String email
) {
}
