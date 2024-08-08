package com.rememberme.rememberMe.presenters;

import com.rememberme.rememberMe.domain.Alert;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FolderTasksResponsePresenter(
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
