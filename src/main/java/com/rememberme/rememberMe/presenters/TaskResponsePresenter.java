package com.rememberme.rememberMe.presenters;

import java.time.LocalDateTime;

public record TaskResponsePresenter(
        Long id,
        String name,
        String description,
        Float coast,
        LocalDateTime alertAt
) {
}
