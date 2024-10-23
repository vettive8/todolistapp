package com.todolist.dao.jpa;

import com.todolist.dao.CategoryDao;
import com.todolist.model.Category;
import com.todolist.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class JpaCategoryDao implements CategoryDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Category create(Category category) {
        em.persist(category);
        return category;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(em.find(Category.class, id));
    }

    @Override
    public List<Category> findByUser(User user) {
        return em.createQuery("SELECT c FROM Category c WHERE c.user = :user", Category.class)
                 .setParameter("user", user)
                 .getResultList();
    }

    @Override
    public Category update(Category category) {
        return em.merge(category);
    }

    @Override
    public void delete(Category category) {
        em.remove(em.contains(category) ? category : em.merge(category));
    }
}
