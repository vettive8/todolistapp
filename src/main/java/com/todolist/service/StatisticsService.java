package com.todolist.service;

import com.todolist.model.User;

import java.util.Map;

public interface StatisticsService {
    int getCompletedTasksCount(User user);
    int getActiveTasksCount(User user);
    int getOverdueTasksCount(User user);
    Map<String, Integer> getTasksCountByCategory(User user);
    Map<String, Integer> getTasksCountByPriority(User user);
}
