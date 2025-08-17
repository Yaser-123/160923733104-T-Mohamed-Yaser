package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Task;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Optional<Task> getTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean updateTask(Task updatedTask) {
        Optional<Task> existing = getTaskById(updatedTask.getId());
        if (existing.isPresent()) {
            Task task = existing.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            return true;
        }
        return false;
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(t -> t.getId() == id);
    }
}
