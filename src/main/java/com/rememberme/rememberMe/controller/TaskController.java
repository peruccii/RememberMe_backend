package com.rememberme.rememberMe.controller;

import com.rememberme.rememberMe.dtos.TaskRequestDTO;
import com.rememberme.rememberMe.presenters.FolderTasksResponsePresenter;
import com.rememberme.rememberMe.presenters.TaskResponsePresenter;
import com.rememberme.rememberMe.presenters.TasksFilteredResponsePresenter;
import com.rememberme.rememberMe.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create-task/{folderId}")
    public ResponseEntity<TaskResponsePresenter> create(
            @RequestBody TaskRequestDTO payload, @PathVariable Long folderId, JwtAuthenticationToken token) {
        return this.taskService.createTask(payload, folderId, UUID.fromString(token.getName()));
    }

    @GetMapping("/{folderId}")
    public ResponseEntity<List<FolderTasksResponsePresenter>> getFolderTasks(
            @PathVariable Long folderId) {
        return this.taskService.getFolderTasks(folderId);
    }

    @GetMapping
    public ResponseEntity<List<TasksFilteredResponsePresenter>> getTasksFiltered(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String type_alert
    ) {
        return this.taskService.getAllTasks(page, size, type_alert);
    }
}
