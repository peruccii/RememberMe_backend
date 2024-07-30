package com.rememberme.rememberMe.services;

import com.rememberme.rememberMe.domain.Folder;
import com.rememberme.rememberMe.dtos.FolderRequestDTO;
import com.rememberme.rememberMe.presenters.FolderResponsePresenter;
import com.rememberme.rememberMe.repositories.IFolderRepository;
import com.rememberme.rememberMe.strategy.pack.FolderStrategyInterface;
import com.rememberme.rememberMe.strategy.pack.UserStrategyInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FolderService {


    private final FolderStrategyInterface folderStrategy;

    private final UserStrategyInterface userStrategy;

    private final IFolderRepository folderRepository;

    public FolderService(FolderStrategyInterface folderStrategy, UserStrategyInterface userStrategy, IFolderRepository folderRepository) {
        this.folderStrategy = folderStrategy;
        this.userStrategy = userStrategy;
        this.folderRepository = folderRepository;
    }

    public ResponseEntity<FolderResponsePresenter> createFolder(FolderRequestDTO payload, JwtAuthenticationToken token) {
        var userResponse = this.userStrategy.validateIfUserExists("id", token.getName());
        this.folderStrategy.validateTitle(payload.title());
        this.folderStrategy.validateSameTitle(payload.title(), UUID.fromString(token.getName()));

        var folder = new Folder();

        folder.setTitle(payload.title());
        folder.setUser(userResponse);
        folderRepository.save(folder);

        return ResponseEntity.status(HttpStatus.CREATED).body(new FolderResponsePresenter(
                folder.getId(),
                folder.getTitle(),
                folder.getUser().getId(),
                folder.getUser().getName()
        ));
    }

    public List<Folder> consultFolders(UUID userId) {
        return this.folderRepository.findByUserId(userId);
    }
}
