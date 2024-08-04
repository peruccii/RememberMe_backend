package com.rememberme.rememberMe.scheduledJob;

import com.rememberme.rememberMe.domain.Task;
import com.rememberme.rememberMe.repositories.ITaskRepository;
import com.rememberme.rememberMe.services.AlertService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class Verification {

    private final ITaskRepository taskRepository;
    private final AlertService alertService;

    public Verification(ITaskRepository taskRepository, AlertService alertService) {
        this.taskRepository = taskRepository;
        this.alertService = alertService;
    }

    @Scheduled(fixedDelay = 3600000) // TODO: Move fixedDelay to env variable
    public void verify(){

        ZoneId brazilZoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime nowInBrazil = ZonedDateTime.now(brazilZoneId);
        LocalDate todayInBrazil = nowInBrazil.toLocalDate();

        // A list of tasks with the sames dates today
        List<Task> tasks = this.taskRepository.findByAlertAt(todayInBrazil);

        // A list of tasks ids with the sames dates today
        List<Long> tasksIds = tasks.stream().map(Task::getId).toList();

        this.alertService.sendAlert(tasksIds);

    }
}

