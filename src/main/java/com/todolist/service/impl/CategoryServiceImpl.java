package com.todolist.service.impl;

import com.todolist.dao.CategoryDao;
import com.todolist.model.Category;
import com.todolist.model.User;
import com.todolist.service.CategoryService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class CategoryServiceImpl implements CategoryService {

    @Inject
    private CategoryDao categoryDao;

    @Override
    public Category createCategory(Category category) {
        return categoryDao.create(category);
    }

    @Override
    public Optional<Category> findCategoryById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public List<Category> findCategoriesByUser(User user) {
        return categoryDao.findByUser(user);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryDao.update(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryDao.delete(category);
    }
}
