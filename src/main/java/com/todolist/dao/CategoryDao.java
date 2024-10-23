package com.todolist.dao;

import com.todolist.model.Category;
import com.todolist.model.User;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {
    Category create(Category category);
    Optional<Category> findById(Long id);
    List<Category> findByUser(User user);
    Category update(Category category);
    void delete(Category category);
}
