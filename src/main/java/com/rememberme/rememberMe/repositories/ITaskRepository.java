package com.rememberme.rememberMe.repositories;

import com.rememberme.rememberMe.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByName(String name);

    @Query("SELECT t FROM Task t WHERE t.alertAt = :today")
    List<Task> findByAlertAt(LocalDate today);

    List<Task> findAllByFolderId(Long folderId);
}
