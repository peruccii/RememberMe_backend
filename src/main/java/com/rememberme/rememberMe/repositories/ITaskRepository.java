package com.rememberme.rememberMe.repositories;

import com.rememberme.rememberMe.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long> {
}
