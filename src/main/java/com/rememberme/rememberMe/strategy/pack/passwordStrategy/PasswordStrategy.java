package com.rememberme.rememberMe.strategy.pack.passwordStrategy;

import java.util.List;

/**
 * Interface {@code PasswordStrategy} defines the contract for password validation strategies.
 * <p>
 * This interface specifies methods for validating passwords against various criteria. Implementations of this interface should provide specific logic for each validation method to ensure passwords meet the required standards.
 * </p>
 *
 * <p>
 * The criteria include:
 * <ul>
 *     <li>Minimum character length</li>
 *     <li>Presence of at least one uppercase character</li>
 *     <li>Presence of at least one lowercase character</li>
 *     <li>Presence of at least one numeric character</li>
 *     <li>Presence of at least one special character</li>
 * </ul>
 * </p>
 *
 * @version 1.0
 * @since 2024-07-22
 */

public interface PasswordStrategy {

    /**
     * Validates that the password has a minimum number of characters.
     *
     * @param password the password to validate
     * @param failures the list to store validation failure messages
     */
    void minCharactersValidator(String password, List<String> failures);

    /**
     * Validates that the password contains at least one uppercase character.
     *
     * @param password the password to validate
     * @param failures the list to store validation failure messages
     */
    void upperCaseCharacterValidator(String password, List<String> failures);

    /**
     * Validates that the password contains at least one lowercase character.
     *
     * @param password the password to validate
     * @param failures the list to store validation failure messages
     */
    void lowerCaseCharacterValidator(String password, List<String> failures);

    /**
     * Validates that the password contains at least one numeric character.
     *
     * @param password the password to validate
     * @param failures the list to store validation failure messages
     */
    void numberCharacterValidator(String password, List<String> failures);

    /**
     * Validates that the password contains at least one special character.
     *
     * @param password the password to validate
     * @param failures the list to store validation failure messages
     */
    void specialCharacterValidator(String password, List<String> failures);
}
