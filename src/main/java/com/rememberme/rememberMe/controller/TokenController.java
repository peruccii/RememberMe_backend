
package com.rememberme.rememberMe.controller;

import com.rememberme.rememberMe.dtos.AuthRequestDTO;
import com.rememberme.rememberMe.presenters.AuthResponsePresenter;
import com.rememberme.rememberMe.services.AuthService;
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
    public ResponseEntity<AuthResponsePresenter> auth(@RequestBody AuthRequestDTO authPayload) {
        return this.authService.authenticate(authPayload);
    }

}