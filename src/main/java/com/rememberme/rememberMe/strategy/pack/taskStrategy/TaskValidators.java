package com.rememberme.rememberMe.strategy.pack.taskStrategy;

import com.rememberme.rememberMe.domain.Task;
import com.rememberme.rememberMe.domain.User;
import com.rememberme.rememberMe.exceptions.DataAlreadyExistsException;
import com.rememberme.rememberMe.exceptions.DataNotFoundExcpetion;
import com.rememberme.rememberMe.repositories.ITaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TaskValidators implements TaskValidatorsInterface {

    private final ITaskRepository taskRepository;

    public TaskValidators(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void validateSameTitle(String taskName) {
        var task = this.taskRepository.findByName(taskName);
        if (task.stream().anyMatch(taskMap -> taskMap.getName().equals(taskName))) {
            throw new DataAlreadyExistsException("Task with name " + taskName + " already exists");
        }
    }

    @Override
    public Task validateIfTaskExists(String typeVerification, String value) {
        switch (typeVerification) {
            case "id" -> {
                return this.taskRepository.findById(Long.parseLong(value)).orElseThrow(
                        () -> new DataNotFoundExcpetion("TASK DOES NOT EXISTS"));
            }
            case "name" -> {
                return this.taskRepository.findByName(value).orElseThrow(
                        () -> new DataNotFoundExcpetion("TASK DOES NOT EXISTS"));
            }
            default -> throw new RuntimeException("NO TYPE VERIFICATION SELECTED");
        }
    }
}
