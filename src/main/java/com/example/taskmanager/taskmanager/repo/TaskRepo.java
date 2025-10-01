package com.example.taskmanager.taskmanager.repo;

import com.example.taskmanager.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaskRepo extends JpaRepository<Task,Long> {
    List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
