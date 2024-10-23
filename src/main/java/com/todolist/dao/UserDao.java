package com.todolist.dao;

import com.todolist.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    User create(User user);
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    User update(User user);
    void delete(User user);
}
