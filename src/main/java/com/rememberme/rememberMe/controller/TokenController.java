
package com.rememberme.rememberMe.controller;

import com.rememberme.rememberMe.dtos.AuthRequestDTO;
import com.rememberme.rememberMe.dtos.UserRequestDTO;
import com.rememberme.rememberMe.presenters.AuthResponsePresenter;
import com.rememberme.rememberMe.presenters.UserResponsePresenter;
import com.rememberme.rememberMe.services.AuthService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    private final JwtEncoder jwtEncoder;

    @Autowired
    private AuthService authService;

    public TokenController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthResponsePresenter> auth(@RequestBody @Valid  AuthRequestDTO authPayload) {
        return this.authService.authenticate(authPayload);
    }

    @Transactional
    @PostMapping("/user")
    public ResponseEntity<UserResponsePresenter> create(@RequestBody @Valid  UserRequestDTO userPayload) {
        return this.authService.createUser(userPayload);
    }

}