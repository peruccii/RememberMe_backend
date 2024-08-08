package com.rememberme.rememberMe.services;

import com.rememberme.rememberMe.domain.Alert;
import com.rememberme.rememberMe.domain.Task;
import com.rememberme.rememberMe.exceptions.DataAlreadyExistsException;
import com.rememberme.rememberMe.repositories.IAlertRepository;
import com.rememberme.rememberMe.repositories.ITaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlertService {

    private final ITaskRepository taskRepository;
    private final IAlertRepository alertRepository;

    public AlertService(ITaskRepository taskRepository, IAlertRepository alertRepository) {
        this.taskRepository = taskRepository;
        this.alertRepository = alertRepository;
    }

    public void AlertAlreadyExists(List<Long> taskIds) {
        var tasks = taskRepository.findAllById(taskIds);
        var existingTaskIds = tasks.stream().map(Task::getId).collect(Collectors.toSet());

        if (taskIds.stream().anyMatch(existingTaskIds::contains)) {
            return;
        }
    }

    @Transactional // Used in cases of +1 operations
    public void sendAlert(List<Long> taskIds) {

        this.AlertAlreadyExists(taskIds);

        List<Task> tasksToSendAlert = this.taskRepository.findAllById(taskIds);

        tasksToSendAlert.forEach(map -> {
            Alert alert = new Alert();
            alert.setTitle(map.getName());
            alert.setType_alert(Alert.Values.TODAY.name());
            alert.setTask(map);
            map.setAlert(alert);
            this.alertRepository.save(alert);
            this.taskRepository.saveAndFlush(map); // save and synchronize data with persistence jpa
        });

    }

}
