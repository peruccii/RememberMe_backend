
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

/**
 * Class {@code TokenController} is responsible for handling authentication and user creation.
 * <p>
 *  It contains specific methods for authentication and user creation using authentication services.
 * </p>
 *
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

@RestController

public class TokenController {

    private final JwtEncoder jwtEncoder;

    private final AuthService authService;

    public TokenController(JwtEncoder jwtEncoder, AuthService authService) {
        this.jwtEncoder = jwtEncoder;
        this.authService = authService;
    }

    /**
     * Authenticates a user with the provided credentials.
     * @return a response containing the authentication token
     */
    @PostMapping("/auth")
    public ResponseEntity<AuthResponsePresenter> auth(@Valid @RequestBody AuthRequestDTO authPayload) {
        return this.authService.authenticate(authPayload);
    }

    /**
     * Creates a new user with the provided data.
     * @return a response containing the created user's information
     */
    @Transactional
    @PostMapping("/user")
    public ResponseEntity<UserResponsePresenter> create(@Valid @RequestBody UserRequestDTO userPayload) {
        return this.authService.createUser(userPayload);
    }

}