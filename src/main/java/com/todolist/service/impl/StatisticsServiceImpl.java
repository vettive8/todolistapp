package com.todolist.service.impl;

import com.todolist.dao.TaskDao;
import com.todolist.model.Task;
import com.todolist.model.User;
import com.todolist.service.StatisticsService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Stateless
public class StatisticsServiceImpl implements StatisticsService {

    @Inject
    private TaskDao taskDao;

    @Override
    public int getCompletedTasksCount(User user) {
        return taskDao.findByUserAndStatus(user, Task.Status.COMPLETED).size();
    }

    @Override
    public int getActiveTasksCount(User user) {
        return taskDao.findByUserAndStatus(user, Task.Status.ACTIVE).size();
    }

    @Override
    public int getOverdueTasksCount(User user) {
        return (int) taskDao.findByUser(user).stream()
                .filter(task -> task.getStatus() == Task.Status.ACTIVE && task.getDueDate().isBefore(LocalDateTime.now()))
                .count();
    }

    @Override
    public Map<String, Integer> getTasksCountByCategory(User user) {
        return taskDao.findByUser(user).stream()
                .collect(Collectors.groupingBy(
                        task -> task.getCategory().getName(),
                        Collectors.summingInt(e -> 1)
                ));
    }

    @Override
    public Map<String, Integer> getTasksCountByPriority(User user) {
        return taskDao.findByUser(user).stream()
                .collect(Collectors.groupingBy(
                        task -> task.getPriority().toString(),
                        Collectors.summingInt(e -> 1)
                ));
    }
}
