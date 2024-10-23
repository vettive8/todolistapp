package com.todolist.service;

import com.todolist.model.Task;
import com.todolist.model.User;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface TaskService {
    Task createTask(Task task);
    Optional<Task> findTaskById(Long id);
    List<Task> findTasksByUser(User user);
    List<Task> findTasksByUserAndStatus(User user, Task.Status status);
    List<Task> findTasksByUserAndPriority(User user, Task.Priority priority);
    List<Task> findTasksByUserAndCategory(User user, Long categoryId);
    Task updateTask(Task task);
    void deleteTask(Task task);
    void completeTask(Task task);
    void archiveTask(Task task);
    List<Task> findTasksDueBefore(LocalDateTime dateTime);
}
