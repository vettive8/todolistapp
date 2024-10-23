package com.todolist.scheduler;

import com.todolist.model.Task;
import com.todolist.service.EmailService;
import com.todolist.service.TaskService;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Singleton
public class TaskReminderScheduler {

    @Inject
    private TaskService taskService;

    @Inject
    private EmailService emailService;

    @Schedule(hour = "*", minute = "0", persistent = false)
    public void sendTaskReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderThreshold = now.plusDays(1);

        List<Task> upcomingTasks = taskService.findTasksDueBefore(reminderThreshold);

        for (Task task : upcomingTasks) {
            emailService.sendTaskReminder(task.getUser(), task);
        }
    }
}
