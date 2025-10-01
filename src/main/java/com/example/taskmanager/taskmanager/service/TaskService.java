package com.example.taskmanager.taskmanager.service;

import com.example.taskmanager.taskmanager.model.Task;
import com.example.taskmanager.taskmanager.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepo repository;

    @Autowired
    public TaskService(TaskRepo repository){
        this.repository=repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }


    public Task getTaskById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Task addTask(Task task) {
        return repository.save(task);
    }

    public Task updateTaskById(Long id, Task task) {
        Optional<Task> existingTask=repository.findById(id);
        if(existingTask.isPresent()){
            Task currentTask=existingTask.get();
            currentTask.setTitle(task.getTitle());
            currentTask.setDescription(task.getDescription());
            currentTask.setStatus(task.getStatus());
            return repository.save(currentTask);
        }else{
            return null;
        }
    }

    public boolean deleteTaskById(long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public List<Task> searchTasks(String keyword) {
        return repository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

}
