package com.rememberme.rememberMe.services;

import com.rememberme.rememberMe.domain.Folder;
import com.rememberme.rememberMe.dtos.FolderRequestDTO;
import com.rememberme.rememberMe.presenters.UserFoldersPresenter;
import com.rememberme.rememberMe.strategy.pack.userStrategy.UserStrategyInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserStrategyInterface userStrategy;

    private final FolderService folderService;

    public UserService(UserStrategyInterface userStrategy, FolderService folderService) {
        this.userStrategy = userStrategy;
        this.folderService = folderService;
    }

    public ResponseEntity<UserFoldersPresenter> listUserFolders(JwtAuthenticationToken token) {
        var userResponse = this.userStrategy.validateUserExists("id", token.getName());

        List<Folder> folders = this.folderService.consultFolders(UUID.fromString(token.getName()));

        List<FolderRequestDTO> foldersPresenter = folders.stream().map(folder -> new FolderRequestDTO(
                folder.getTitle()
        )).toList();

        return ResponseEntity.ok(new UserFoldersPresenter(
                userResponse.getId(),
                userResponse.getName(),
                userResponse.getEmail(),
                foldersPresenter
        ));
    }
}
