package com.rememberme.rememberMe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rememberme.rememberMe.domain.enums.TypeSituationUser;
import com.rememberme.rememberMe.dtos.AuthRequestDTO;
import com.rememberme.rememberMe.dtos.EmailSenderDTO;
import com.rememberme.rememberMe.dtos.UserRequestDTO;
import com.rememberme.rememberMe.exceptions.DataNotFoundExcpetion;
import com.rememberme.rememberMe.presenters.AuthResponsePresenter;
import com.rememberme.rememberMe.presenters.PasswordFailurePresenter;
import com.rememberme.rememberMe.presenters.UserResponsePresenter;
import com.rememberme.rememberMe.producers.EmailProducer;
import com.rememberme.rememberMe.repositories.IUserRepository;
import com.rememberme.rememberMe.strategy.pack.UserStrategyInterface;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Service class {@code AuthService} is responsible for handling user authentication and creation.
 * <p>
 *  It provides methods to create a new user and authenticate an existing user using JWT.
 * </p>
 *
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

@Service
public class AuthService {

    private final IUserRepository userRepository;

    private final  UserStrategyInterface userStrategyInterface;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final EmailProducer emailProducer;

    private final JwtEncoder jwtEncoder;

    public AuthService(IUserRepository userRepository, UserStrategyInterface userStrategyInterface, BCryptPasswordEncoder bCryptPasswordEncoder, EmailProducer emailProducer, EmailSender smtpMailSender, JwtEncoder jwtEncoder) {
        this.userRepository = userRepository;
        this.userStrategyInterface = userStrategyInterface;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailProducer = emailProducer;
        this.jwtEncoder = jwtEncoder;
    }

    public ResponseEntity<UserResponsePresenter> createUser( UserRequestDTO userPayload) throws JsonProcessingException {
//        this.userStrategyInterface.validateIfUserExists("email", userPayload.email());

        this.userStrategyInterface.validate(userPayload.password());

        var user = this.userRepository.save(userPayload.toUser(bCryptPasswordEncoder));

        this.emailProducer.sendEmailProducer(new EmailSenderDTO(
                userPayload.email()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponsePresenter(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getSituation()
        ));
    }

    public ResponseEntity<AuthResponsePresenter> authenticate(AuthRequestDTO authPayload) {
        var user = userRepository.findByEmail(authPayload.email());

        if (user.isEmpty() || !user.get().isAuthCorrect(authPayload, bCryptPasswordEncoder))
            throw new DataNotFoundExcpetion("EMAIL OR PASSWORD INCORRECT");


        var claims = JwtClaimsSet.builder()
                .issuer("remember_me")
                .subject(user.get().getId().toString())
                .expiresAt(Instant.now().plusSeconds(3000L))
                .issuedAt(Instant.now()).build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new AuthResponsePresenter(jwtValue, 3000L));
    }
}
