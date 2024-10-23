package com.todolist.service;

import com.todolist.model.Task;
import com.todolist.model.User;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless
public class EmailService {

    private static final String FROM_EMAIL = "your-email@example.com";
    private static final String PASSWORD = "your-email-password";

    public void sendTaskReminder(User user, Task task) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Task Reminder: " + task.getTitle());
            message.setText("Dear " + user.getUsername() + ",\n\n"
                    + "This is a reminder for your task: " + task.getTitle() + "\n"
                    + "Description: " + task.getDescription() + "\n"
                    + "Due Date: " + task.getDueDate() + "\n\n"
                    + "Please complete this task as soon as possible.\n\n"
                    + "Best regards,\nYour To-Do List App");

            Transport.send(message);

            System.out.println("Reminder email sent to " + user.getEmail());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
