package com.rememberme.rememberMe.strategy.pack;

import com.rememberme.rememberMe.domain.User;

import java.util.Optional;
import java.util.UUID;

/**
 * Class {@code UserStrategyInterface} control the methods of UserStrategy.
 * <p>
 *  It contain the methods that need be used in UserStrategy
 * </p>
 *
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

public interface UserStrategyInterface {

    /**
     * Validates the given password according to specific criteria.
     *
     * @param password the password to validate
     */
    void validate(String password);

    /**
     * Validates the given email according to specific criteria.
     *
     * @param typeVerification is used to verify if the user exists in UserRepository
     * @return
     */

    User validateIfUserExists(String typeVerification, String value);

}
