package com.rememberme.rememberMe.services;

import com.rememberme.rememberMe.domain.Alert;
import com.rememberme.rememberMe.domain.Task;
import com.rememberme.rememberMe.repositories.ITaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    private final ITaskRepository taskRepository;

    public AlertService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void sendAlert(List<Long> taskIds) {

        List<Task> tasksToSendAlert = this.taskRepository.findAllById(taskIds);

        tasksToSendAlert.forEach(map -> {
            Alert alert = new Alert();
            alert.setTitle(map.getName());
            alert.setType_alert(Alert.Values.OK.name());
        });

    }

}
