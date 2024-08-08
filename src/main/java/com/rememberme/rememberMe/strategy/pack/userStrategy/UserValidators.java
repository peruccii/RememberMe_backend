package com.rememberme.rememberMe.strategy.pack.userStrategy;

import com.rememberme.rememberMe.domain.User;
import com.rememberme.rememberMe.exceptions.DataAlreadyExistsException;
import com.rememberme.rememberMe.exceptions.DataBadRequestExcpetion;
import com.rememberme.rememberMe.exceptions.DataNotFoundExcpetion;
import com.rememberme.rememberMe.repositories.IUserRepository;
import com.rememberme.rememberMe.strategy.pack.passwordStrategy.PasswordStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class UserValidators implements UserValidatorsInterface{

    private final PasswordStrategy passwordStrategy;
    private final IUserRepository userRepository;

    public UserValidators(PasswordStrategy passwordStrategy, IUserRepository userRepository) {
        this.passwordStrategy = passwordStrategy;
        this.userRepository = userRepository;
    }

    @Override
    public void validateIsEmail(String email) {
        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", email)) {
            throw new DataBadRequestExcpetion("INVALID EMAIL");
        }
    }

    @Override
    public void validateIfEmailAlreadyExists(String email) {
        var user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new DataAlreadyExistsException("EMAIL ALREADY EXISTS");
        }
    }

    @Override
    public void validatePassword(String password) {
        List<String> failures = new ArrayList<>();

        this.passwordStrategy.minCharactersValidator(password, failures);
        this.passwordStrategy.lowerCaseCharacterValidator(password, failures);
        this.passwordStrategy.upperCaseCharacterValidator(password, failures);
        this.passwordStrategy.specialCharacterValidator(password, failures);
        this.passwordStrategy.numberCharacterValidator(password, failures);

        if (!failures.isEmpty()) throw new DataBadRequestExcpetion(failures.toString());

    }

    @Override
        public User validateIfUserExists(String typeVerification, String value) {
            switch (typeVerification) {
                case "id" -> {
                    return this.userRepository.findById(UUID.fromString(value)).orElseThrow(
                            () -> new DataNotFoundExcpetion("USER DOES NOT EXISTS"));
                }
                case "email" -> {
                    return this.userRepository.findByEmail(value).orElseThrow(
                            () -> new DataNotFoundExcpetion("USER DOES NOT EXISTS"));
               }
                default -> throw new RuntimeException("NO TYPE VERIFICATION SELECTED");
            }
        }
}
