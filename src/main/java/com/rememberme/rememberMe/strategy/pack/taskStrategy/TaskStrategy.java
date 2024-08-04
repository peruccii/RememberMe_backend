package com.rememberme.rememberMe.strategy.pack.taskStrategy;

import com.rememberme.rememberMe.exceptions.DataBadRequestExcpetion;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class TaskStrategy {

    @Component
    public static class TaskValidations implements TaskStrategyInterface {

        private final TaskValidators taskValidators;

        public TaskValidations(TaskValidators taskValidators) {
            this.taskValidators = taskValidators;
        }

        @Override
        public void validate(String value) {
            List<String> failures = new ArrayList<>();

            this.taskValidators.validateIfExists(value, failures);
            this.taskValidators.validateSameTitle(value, failures);

            if (!failures.isEmpty()) throw new DataBadRequestExcpetion(failures.toString());

        }
    }
}
