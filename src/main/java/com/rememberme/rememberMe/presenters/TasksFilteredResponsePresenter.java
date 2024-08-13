package com.rememberme.rememberMe.presenters;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record TasksFilteredResponsePresenter(
        Long id,
        String title,
        String description,
        Float coast,
        LocalDate alertAt,
        Object alert,
        Long folderId,
        LocalDateTime createdAt
) {
}
