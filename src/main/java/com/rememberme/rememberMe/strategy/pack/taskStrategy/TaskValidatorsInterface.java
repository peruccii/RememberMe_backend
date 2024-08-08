package com.rememberme.rememberMe.strategy.pack.taskStrategy;
import com.rememberme.rememberMe.domain.Task;

import java.util.List;

public interface TaskValidatorsInterface {
    void validateSameTitle(String taskName);
    Task validateIfTaskExists(String typeVerification, String value);
}
