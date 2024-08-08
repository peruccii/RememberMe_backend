package com.rememberme.rememberMe.strategy.pack.taskStrategy;

import com.rememberme.rememberMe.domain.Task;

public interface TaskStrategyInterface {
    Task validateTaskExists(String typeVerification, String value);
    void validateSameTitleTask(String value);
}
