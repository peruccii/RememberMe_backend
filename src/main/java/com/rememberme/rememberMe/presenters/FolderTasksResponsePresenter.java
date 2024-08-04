package com.rememberme.rememberMe.presenters;

import com.rememberme.rememberMe.domain.Alert;

import java.time.LocalDateTime;

public record FolderTasksResponsePresenter(
        Long id,
        String title,
        String description,
        Float coast,
        LocalDateTime alertAt,
        Alert alert,
        Long folderId
) {
}
