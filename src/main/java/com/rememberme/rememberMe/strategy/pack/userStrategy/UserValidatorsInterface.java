package com.rememberme.rememberMe.strategy.pack.userStrategy;

import com.rememberme.rememberMe.domain.User;

public interface UserValidatorsInterface {

    /**
     * Validates the given email according to specific criteria.
     *
     * @param email the email to validate
     */

    void validateIsEmail(String email);

    /**
     * Validates the given email according to specific criteria.
     *
     * @param email the email to validate
     */

    void validateIfEmailAlreadyExists(String email);

    /**
     * Validates the given password according to specific criteria.
     *
     * @param password the password to validate
     */

    void validatePassword(String password);
    User validateIfUserExists(String typeVerification, String value);
}
