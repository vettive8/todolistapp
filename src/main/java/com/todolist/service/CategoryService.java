package com.todolist.service;

import com.todolist.model.Category;
import com.todolist.model.User;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(Category category);
    Optional<Category> findCategoryById(Long id);
    List<Category> findCategoriesByUser(User user);
    Category updateCategory(Category category);
    void deleteCategory(Category category);
}
