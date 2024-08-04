package com.rememberme.rememberMe.strategy.pack.taskStrategy;
import java.util.List;

public interface TaskValidatorsInterface {
    void validateSameTitle(String taskName, List<String> failures);
    void validateIfExists(String title, List<String> failures);
}
