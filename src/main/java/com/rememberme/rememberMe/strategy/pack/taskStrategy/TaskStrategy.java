package com.rememberme.rememberMe.strategy.pack.taskStrategy;

import com.rememberme.rememberMe.domain.Task;
import org.springframework.stereotype.Component;


public class TaskStrategy {

    @Component
    public static class TaskValidations implements TaskStrategyInterface {

        private final TaskValidators taskValidators;

        public TaskValidations(TaskValidators taskValidators) {
            this.taskValidators = taskValidators;
        }

//        @Override
//        public void validate(String value) {
//            List<String> failures = new ArrayList<>();
//
//            this.taskValidators.validateIfExists(value);
//            this.taskValidators.validateSameTitle(value);
//
//            if (!failures.isEmpty()) throw new DataBadRequestExcpetion(failures.toString());
//
//        }

        @Override
        public Task validateTaskExists(String typeVerification, String value) {
            return this.taskValidators.validateIfTaskExists(typeVerification, value);
        }

        @Override
        public void validateSameTitleTask(String value) {
           this.taskValidators.validateSameTitle(value);
        }
    }
}
