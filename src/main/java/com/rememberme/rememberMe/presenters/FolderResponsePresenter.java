package com.rememberme.rememberMe.presenters;

import com.rememberme.rememberMe.domain.User;

import java.util.UUID;

public record FolderResponsePresenter(Long id, String folderName, UUID user_id, String user) {
}
