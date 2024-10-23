package com.todolist.dao;

import com.todolist.model.Task;
import com.todolist.model.User;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface TaskDao {
    Task create(Task task);
    Optional<Task> findById(Long id);
    List<Task> findByUser(User user);
    List<Task> findByUserAndStatus(User user, Task.Status status);
    List<Task> findByUserAndPriority(User user, Task.Priority priority);
    List<Task> findByUserAndCategory(User user, Long categoryId);
    Task update(Task task);
    void delete(Task task);
    List<Task> findTasksDueBefore(LocalDateTime dateTime);
}
