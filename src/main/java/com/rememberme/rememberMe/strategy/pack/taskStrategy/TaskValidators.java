package com.rememberme.rememberMe.strategy.pack.taskStrategy;

import com.rememberme.rememberMe.domain.Task;
import com.rememberme.rememberMe.exceptions.DataAlreadyExistsException;
import com.rememberme.rememberMe.exceptions.DataNotFoundExcpetion;
import com.rememberme.rememberMe.repositories.ITaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskValidators implements TaskValidatorsInterface {

    private final ITaskRepository taskRepository;

    public TaskValidators(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void validateSameTitle(String taskName, List<String> failures) {
        var task = this.taskRepository.findByName(taskName);
        if (task.stream().anyMatch(taskMap -> taskMap.getName().equals(taskName))) {
            failures.add("TASK ALREADY EXISTS");
        }
    }

    @Override
    public void validateIfExists(String title, List<String> failures) {
        var task = this.taskRepository.findByName(title);
        if (task.isEmpty()) failures.add("TASK NOT EXISTS");
    }
}
