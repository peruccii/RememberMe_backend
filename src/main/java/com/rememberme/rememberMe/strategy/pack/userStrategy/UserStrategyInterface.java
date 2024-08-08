package com.rememberme.rememberMe.strategy.pack.userStrategy;

import com.rememberme.rememberMe.domain.User;

public interface UserStrategyInterface {
    void validateEmail(String value);
    void validatePassword(String value);
    User validateUserExists(String typeVerification, String value);
}
