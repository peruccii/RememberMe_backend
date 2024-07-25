package com.rememberme.rememberMe.strategy.pack;

import com.rememberme.rememberMe.domain.User;
import com.rememberme.rememberMe.exceptions.DataAlreadyExistsException;
import com.rememberme.rememberMe.exceptions.DataBadRequestExcpetion;
import com.rememberme.rememberMe.presenters.PasswordFailurePresenter;
import com.rememberme.rememberMe.repositories.IUserRepository;
import com.rememberme.rememberMe.strategy.pack.passwordStrategy.PasswordStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Class {@code UserStrategy} manages all user-related strategies.
 * <p>
 * The {@code UserStrategy} class is responsible for managing different strategies related to user operations. It contains an inner service class {@code UserValidations} which implements the validation logic for user passwords.
 * </p>
 *
 * <p>
 * The {@code UserValidations} class uses various password validation strategies implemented in the {@code PasswordStrategy} interface. These strategies check for minimum character length, presence of lowercase and uppercase characters, special characters, and numeric characters.
 * </p>
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

public class UserStrategy {

    @Service
    public static class UserValidations implements UserStrategyInterface {

        @Autowired
        private IUserRepository userRepository;

        private final PasswordStrategy passwordStrategy;

        public UserValidations(PasswordStrategy passwordStrategy) {
            this.passwordStrategy = passwordStrategy;
        }

        @Override
        public void validate(String password) {
            List<String> failures = new ArrayList<>();

            this.passwordStrategy.minCharactersValidator(password, failures);
            this.passwordStrategy.lowerCaseCharacterValidator(password, failures);
            this.passwordStrategy.upperCaseCharacterValidator(password, failures);
            this.passwordStrategy.specialCharacterValidator(password, failures);
            this.passwordStrategy.numberCharacterValidator(password, failures);

           throw new DataBadRequestExcpetion(failures);
        }

        @Override
        public void validateIfUserExists(String email) {
            Optional<User> responseUser = this.userRepository.findByEmail(email);
            responseUser.ifPresent(event -> {
                throw new DataAlreadyExistsException("USER ALREADY EXISTS IN DATABASE");
            });
        }

    }
}
