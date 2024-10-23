package com.todolist.bean;

import com.todolist.model.Category;
import com.todolist.model.Task;
import com.todolist.model.User;
import com.todolist.service.CategoryService;
import com.todolist.service.TaskService;
import com.todolist.service.UserService;
import com.todolist.service.StatisticsService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class TaskManagementBean {

    @Inject
    private TaskService taskService;

    @Inject
    private UserService userService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private StatisticsService statisticsService;

    private User currentUser;
    private List<Task> tasks;
    private List<Category> categories;
    private Task newTask = new Task();
    private Category newCategory = new Category();

    private int completedTasksCount;
    private int activeTasksCount;
    private int overdueTasksCount;
    private Map<String, Integer> tasksCountByCategory;
    private Map<String, Integer> tasksCountByPriority;

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user").toString();
        currentUser = userService.findUserByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        loadTasks();
        loadCategories();
        loadStatistics();
    }

    public void loadTasks() {
        tasks = taskService.findTasksByUser(currentUser);
    }

    public void loadCategories() {
        categories = categoryService.findCategoriesByUser(currentUser);
    }

    public void loadStatistics() {
        completedTasksCount = statisticsService.getCompletedTasksCount(currentUser);
        activeTasksCount = statisticsService.getActiveTasksCount(currentUser);
        overdueTasksCount = statisticsService.getOverdueTasksCount(currentUser);
        tasksCountByCategory = statisticsService.getTasksCountByCategory(currentUser);
        tasksCountByPriority = statisticsService.getTasksCountByPriority(currentUser);
    }

    public String createTask() {
        newTask.setUser(currentUser);
        taskService.createTask(newTask);
        newTask = new Task();
        loadTasks();
        return null;
    }

    public String createCategory() {
        newCategory.setUser(currentUser);
        categoryService.createCategory(newCategory);
        newCategory = new Category();
        loadCategories();
        return null;
    }

    public void completeTask(Task task) {
        taskService.completeTask(task);
        loadTasks();
    }

    public void deleteTask(Task task) {
        taskService.deleteTask(task);
        loadTasks();
    }

    public void deleteCategory(Category category) {
        categoryService.deleteCategory(category);
        loadCategories();
    }

    public Task.Priority[] getPriorities() {
        return Task.Priority.values();
    }

    // Getters and setters
    // ...

    // Add getters for the new statistics fields
    public int getCompletedTasksCount() {
        return completedTasksCount;
    }

    public int getActiveTasksCount() {
        return activeTasksCount;
    }

    public int getOverdueTasksCount() {
        return overdueTasksCount;
    }

    public Map<String, Integer> getTasksCountByCategory() {
        return tasksCountByCategory;
    }

    public Map<String, Integer> getTasksCountByPriority() {
        return tasksCountByPriority;
    }
}
