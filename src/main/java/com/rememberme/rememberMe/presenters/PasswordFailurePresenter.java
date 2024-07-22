package com.rememberme.rememberMe.presenters;

import java.util.List;

/**
 * Class {@code PasswordFailurePresenter} represents the result of a password validation process.
 * <p>
 *  This class is used to encapsulate a list of failure messages that occur during the password validation. Each message
 *  in the list provides a specific reason why the password did not meet the required criteria.
 * </p>
 *
 * @author Eduardo Perucci
 * @version 1.0
 * @since 2024-07-22
 */

public record PasswordFailurePresenter(List<String> failures) {
}
