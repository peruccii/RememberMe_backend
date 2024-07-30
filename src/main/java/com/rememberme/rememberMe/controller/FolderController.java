package com.rememberme.rememberMe.controller;

import com.rememberme.rememberMe.dtos.FolderRequestDTO;
import com.rememberme.rememberMe.presenters.FolderResponsePresenter;
import com.rememberme.rememberMe.services.FolderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/folder")
public class FolderController {

    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping("/create")
    public ResponseEntity<FolderResponsePresenter> create(@RequestBody FolderRequestDTO payload, JwtAuthenticationToken token) {
        return this.folderService.createFolder(payload, token);
    }
}
