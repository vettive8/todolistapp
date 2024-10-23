package com.todolist.service.impl;

import com.todolist.dao.TaskDao;
import com.todolist.model.Task;
import com.todolist.model.User;
import com.todolist.service.TaskService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Stateless
public class TaskServiceImpl implements TaskService {

    @Inject
    private TaskDao taskDao;

    @Override
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(Task.Status.ACTIVE);
        return taskDao.create(task);
    }

    @Override
    public Optional<Task> findTaskById(Long id) {
        return taskDao.findById(id);
    }

    @Override
    public List<Task> findTasksByUser(User user) {
        return taskDao.findByUser(user);
    }

    @Override
    public List<Task> findTasksByUserAndStatus(User user, Task.Status status) {
        return taskDao.findByUserAndStatus(user, status);
    }

    @Override
    public List<Task> findTasksByUserAndPriority(User user, Task.Priority priority) {
        return taskDao.findByUserAndPriority(user, priority);
    }

    @Override
    public List<Task> findTasksByUserAndCategory(User user, Long categoryId) {
        return taskDao.findByUserAndCategory(user, categoryId);
    }

    @Override
    public Task updateTask(Task task) {
        return taskDao.update(task);
    }

    @Override
    public void deleteTask(Task task) {
        taskDao.delete(task);
    }

    @Override
    public void completeTask(Task task) {
        task.setStatus(Task.Status.COMPLETED);
        task.setCompletedAt(LocalDateTime.now());
        taskDao.update(task);
    }

    @Override
    public void archiveTask(Task task) {
        task.setStatus(Task.Status.ARCHIVED);
        taskDao.update(task);
    }

    // Implement the new method
    @Override
    public List<Task> findTasksDueBefore(LocalDateTime dateTime) {
        return taskDao.findTasksDueBefore(dateTime);
    }
}
