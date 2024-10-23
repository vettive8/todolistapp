package com.todolist.service;

import com.todolist.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user) throws Exception;
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    Optional<User> findUserByEmail(String email);
    List<User> getAllUsers();
    User updateUser(User user) throws Exception;
    void deleteUser(User user);
    boolean authenticateUser(String username, String password);
}
