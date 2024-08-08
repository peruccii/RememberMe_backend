package com.rememberme.rememberMe.services;

import com.rememberme.rememberMe.domain.Folder;
import com.rememberme.rememberMe.domain.Task;
import com.rememberme.rememberMe.domain.User;
import com.rememberme.rememberMe.dtos.FolderRequestDTO;
import com.rememberme.rememberMe.dtos.TaskRequestDTO;
import com.rememberme.rememberMe.exceptions.DataNotFoundExcpetion;
import com.rememberme.rememberMe.presenters.AlertResponsePresenter;
import com.rememberme.rememberMe.presenters.FolderTasksResponsePresenter;
import com.rememberme.rememberMe.presenters.TaskResponsePresenter;
import com.rememberme.rememberMe.repositories.IFolderRepository;
import com.rememberme.rememberMe.repositories.ITaskRepository;
import com.rememberme.rememberMe.repositories.IUserRepository;
import com.rememberme.rememberMe.strategy.pack.taskStrategy.TaskStrategyInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskStrategyInterface taskStrategy;

    private final ITaskRepository taskRepository;

    private final IFolderRepository folderRepository;

    private final IUserRepository userRepository;

    public TaskService(TaskStrategyInterface taskStrategy, ITaskRepository taskRepository, IFolderRepository folderRepository, IUserRepository userRepository) {
        this.taskStrategy = taskStrategy;
        this.taskRepository = taskRepository;
        this.folderRepository = folderRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<TaskResponsePresenter> createTask(TaskRequestDTO payload, Long folderId, UUID userId) {
        this.taskStrategy.validateTaskExists("name", payload.name());

        Optional<Folder> folder = Optional.ofNullable(this.folderRepository.findById(folderId)
                .orElseThrow(() -> new DataNotFoundExcpetion("FOLDER NOT FOUND")));

        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundExcpetion("USER NOT FOUND"));

        Task rawTask = new Task();
        rawTask.setName(payload.name());
        rawTask.setDescription(payload.description());
        rawTask.setCoast(payload.coast());
        rawTask.setAlertAt(LocalDate.parse(payload.alert_at(), DateTimeFormatter.ISO_DATE_TIME));
        if (folder.isPresent())
            rawTask.setFolder(folder.get());
        else
            rawTask.setUser(user);

        this.taskRepository.save(rawTask);

        return ResponseEntity.ok(new TaskResponsePresenter(
                rawTask.getId(),
                rawTask.getName(),
                rawTask.getDescription(),
                rawTask.getCoast(),
                rawTask.getAlertAt()
        ));
    }

    public ResponseEntity<List<FolderTasksResponsePresenter>> getFolderTasks(Long folderId){
        List<Task> tasks = this.taskRepository.findAllByFolderId(folderId);

        Object foldersPresenter = tasks.stream().map(task -> new AlertResponsePresenter(
                task.getAlert().getId(),
                task.getAlert().getTitle(),
                task.getAlert().getType_alert()
        )).toArray();

        List<FolderTasksResponsePresenter> folderTasks = tasks.stream().map(map -> new FolderTasksResponsePresenter(
                map.getId(),
                map.getName(),
                map.getDescription(),
                map.getCoast(),
                map.getAlertAt(),
                foldersPresenter,
                map.getFolder().getId(),
                map.getCreatedAt()
        )).toList();

        return ResponseEntity.ok(folderTasks);
    }


    public ResponseEntity<List<FolderTasksResponsePresenter>> getAllTasks(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> tasks = this.taskRepository.findAll(pageable);


        return null;
    }

    public ResponseEntity<TaskResponsePresenter> updateTask(TaskRequestDTO payload, Long taskId) {
        var user = this.taskStrategy.validateTaskExists("id", Long.toString(taskId));

        BeanUtils.copyProperties(payload, user);
        return ResponseEntity.ok(new TaskResponsePresenter(
                user.getId(),
                user.getName(),
                user.getDescription(),
                user.getCoast(),
                user.getAlertAt()
        ));
    }
}
