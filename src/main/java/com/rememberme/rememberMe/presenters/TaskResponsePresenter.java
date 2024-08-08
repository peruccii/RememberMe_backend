package com.rememberme.rememberMe.presenters;

import java.time.LocalDate;

public record TaskResponsePresenter(
        Long id,
        String name,
        String description,
        Float coast,
        LocalDate alertAt
) {
}
