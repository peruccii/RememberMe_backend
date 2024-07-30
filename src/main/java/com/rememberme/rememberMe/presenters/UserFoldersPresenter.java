package com.rememberme.rememberMe.presenters;

import com.rememberme.rememberMe.dtos.FolderRequestDTO;

import java.util.UUID;
import java.util.List;

public record UserFoldersPresenter(
        UUID id,
        String name,
        String email,
        List<FolderRequestDTO> folder
) {
}
