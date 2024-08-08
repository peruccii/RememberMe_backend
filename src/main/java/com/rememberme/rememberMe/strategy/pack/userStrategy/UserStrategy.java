package com.rememberme.rememberMe.strategy.pack.userStrategy;

import com.rememberme.rememberMe.domain.User;
import org.springframework.stereotype.Component;

public class UserStrategy {

    @Component
    public static class UserValidations implements UserStrategyInterface {

        private final UserValidatorsInterface validators;

        public UserValidations(UserValidatorsInterface validators) {
            this.validators = validators;
        }

        @Override
        public void validateEmail(String value) {
            this.validators.validateIsEmail(value);
            this.validators.validateIfEmailAlreadyExists(value);
        }

        @Override
        public void validatePassword(String value) {
            this.validators.validatePassword(value);
        }


        @Override
        public User validateUserExists(String typeVerification, String value) {
            return this.validators.validateIfUserExists(typeVerification, value);
        }
    }

}
