package com.rememberme.rememberMe.scheduledJob;

import com.rememberme.rememberMe.domain.Task;
import com.rememberme.rememberMe.repositories.ITaskRepository;
import com.rememberme.rememberMe.services.AlertService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static com.rememberme.rememberMe.scheduledJob.SplitDateTimeRangeInEqualIntervals.splitDateTimeRangesInEqualIntervals;

@Component
public class Verification {

    private final ITaskRepository taskRepository;
    private final AlertService alertService;

    public Verification(ITaskRepository taskRepository, AlertService alertService) {
        this.taskRepository = taskRepository;
        this.alertService = alertService;
    }

    @Scheduled(fixedDelay = 60000) // TODO: Move fixedDelay to env variable
    public void verify(){

        ZoneId brazilZoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime nowInBrazil = ZonedDateTime.now(brazilZoneId);
        LocalDate todayInBrazilLocal = nowInBrazil.toLocalDate();
        System.out.println(todayInBrazilLocal);
        // A list of tasks with the sames dates today
        List<Task> tasks = this.taskRepository.findByAlertAt(todayInBrazilLocal);

        // A list of tasks ids with the sames dates today
        List<Long> tasksIds = tasks.stream().map(Task::getId).toList();

//        // A list all tasks
//        List<Task> AllTasks = this.taskRepository.findAll();
//
//        // Days to alert of all tasks
//        List<LocalDateTime> daysToAlert =  AllTasks.stream().map(Task::getAlertAt).toList();
//
//        // Days of the creations of the tasks
//        List<LocalDateTime> tasksCreatedAt =  AllTasks.stream().map(Task::getCreatedAt).toList();
//
//
//        // List of dates to send alert
//        List<LocalDateTime> intervals = splitDateTimeRangesInEqualIntervals(
//                tasksCreatedAt, daysToAlert, 3);

        this.alertService.sendAlert(tasksIds);
    }
}

