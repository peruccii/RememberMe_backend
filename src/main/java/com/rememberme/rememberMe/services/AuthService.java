package com.rememberme.rememberMe.services;

import com.rememberme.rememberMe.dtos.AuthRequestDTO;
import com.rememberme.rememberMe.presenters.AuthResponsePresenter;
import com.rememberme.rememberMe.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtEncoder jwtEncoder;


    public ResponseEntity<AuthResponsePresenter> authenticate(AuthRequestDTO authPayload) {
        var user = userRepository.findByEmail(authPayload.email());

        if (user.isEmpty() || !user.get().isAuthCorrect(authPayload, bCryptPasswordEncoder))
            throw new BadCredentialsException("EMAIL OR PASSWORD INCORRECT");


        var claims = JwtClaimsSet.builder()
                .issuer("remember_me")
                .subject(user.get().getId().toString())
                .expiresAt(Instant.now().plusSeconds(300L))
                .issuedAt(Instant.now()).build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new AuthResponsePresenter(jwtValue, 300l));
    }
}
