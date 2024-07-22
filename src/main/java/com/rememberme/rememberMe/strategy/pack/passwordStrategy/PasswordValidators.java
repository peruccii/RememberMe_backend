package com.rememberme.rememberMe.strategy.pack.passwordStrategy;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Class {@code PasswordValidators} implements the {@code PasswordStrategy} interface to provide
 * concrete validation methods for password strength.
 * <p>
 * This class includes various validation methods to ensure that passwords meet specific criteria,
 * such as minimum length, presence of uppercase and lowercase characters, numeric characters,
 * and special characters.
 * </p>
 *
 * @version 1.0
 * @since 2024-07-22
 */

@Service
public class PasswordValidators implements PasswordStrategy {

    @Override
    public void minCharactersValidator(String password, List<String> failures) {
        if (Objects.nonNull(password) && password.length() < 5) failures.add("PASSWORD MUST BE AT LEAST 5 CHARACTERS");
    }

    @Override
    public void upperCaseCharacterValidator(String password, List<String> failures) {
        if (!Pattern.matches(".*[A-Z].*", password)) failures.add("PASSWORD MUST BE AT LEAST 1 UPPERCASE CHARACTER");
    }

    @Override
    public void lowerCaseCharacterValidator(String password, List<String> failures) {
        if (!Pattern.matches(".*[a-z].*", password)) failures.add("PASSWORD MUST BE AT LEAST 1 LOWERCASE CHARACTER");
    }

    @Override
    public void numberCharacterValidator(String password, List<String> failures) {
        if (!Pattern.matches(".*[0-9].*", password)) failures.add("PASSWORD MUST BE AT LEAST 1 NUMBER CHARACTER");
    }

    @Override
    public void specialCharacterValidator(String password, List<String> failures) {
        if (!Pattern.matches(".*[\\W].*", password)) failures.add("PASSWORD MUST BE AT LEAST 1 SPECIAL CHARACTER");
    }
}
