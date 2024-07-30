package com.rememberme.rememberMe.controller;

import com.rememberme.rememberMe.presenters.UserFoldersPresenter;
import com.rememberme.rememberMe.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserFoldersPresenter> getUserFolders(JwtAuthenticationToken token) {
        return this.userService.listUserFolders(token);
    }
}
