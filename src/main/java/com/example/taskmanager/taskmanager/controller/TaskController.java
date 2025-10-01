package com.example.taskmanager.taskmanager.controller;

import com.example.taskmanager.taskmanager.model.Task;
import com.example.taskmanager.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//
//
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//    private TaskService service;
//
//    @Autowired
//    TaskController(TaskService service){
//        this.service=service;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Task>>getAllTasks(){
//        return new ResponseEntity<>(service.getAllTasks(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Task>getTaskById(@PathVariable Long id){
//        Task task=service.getTaskById(id);
//
//        if(task!=null){
//            return new ResponseEntity<>(task,HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Task>addTask(@RequestBody Task task){
//        return new ResponseEntity<>(service.addTask(task),HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Task>updateTask(@PathVariable Long id, Task task){
//        Task updatedTask=service.updateTaskById(id,task);
//        if(updatedTask!=null) {
//            return new ResponseEntity<>(updatedTask, HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void>deleteTask(@PathVariable long id){
//        boolean deleted=service.deleteTaskById(id);
//        if(deleted){
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }else{
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//}
@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = {
        "http://127.0.0.1:5500",
        "https://task-manager-frontend-mu-six.vercel.app"
})


public class TaskController {

    private final TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = service.getTaskById(id);
        return (task != null) ? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task createdTask = service.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = service.updateTaskById(id, task);
        return (updatedTask != null) ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return service.deleteTaskById(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam String keyword) {
        List<Task> tasks = service.searchTasks(keyword);
        return ResponseEntity.ok(tasks);
    }

}
