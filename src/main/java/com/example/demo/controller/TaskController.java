package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Task;
import com.example.demo.services.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable int id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.orElse(null);
    }

    @PostMapping
    public String addTask(@RequestBody Task task) {
        taskService.addTask(task);
        return "Task added successfully";
    }

    @PutMapping
    public String updateTask(@RequestBody Task task) {
        boolean updated = taskService.updateTask(task);
        return updated ? "Task updated successfully" : "Task not found";
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable int id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted ? "Task deleted successfully" : "Task not found";
    }
}
