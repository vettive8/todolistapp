package com.todolist.dao.jpa;

import com.todolist.dao.TaskDao;
import com.todolist.model.Task;
import com.todolist.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Stateless
public class JpaTaskDao implements TaskDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Task create(Task task) {
        em.persist(task);
        return task;
    }

    @Override
    public Optional<Task> findById(Long id) {
        return Optional.ofNullable(em.find(Task.class, id));
    }

    @Override
    public List<Task> findByUser(User user) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user", Task.class)
                 .setParameter("user", user)
                 .getResultList();
    }

    @Override
    public List<Task> findByUserAndStatus(User user, Task.Status status) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user AND t.status = :status", Task.class)
                 .setParameter("user", user)
                 .setParameter("status", status)
                 .getResultList();
    }

    @Override
    public List<Task> findByUserAndPriority(User user, Task.Priority priority) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user AND t.priority = :priority", Task.class)
                 .setParameter("user", user)
                 .setParameter("priority", priority)
                 .getResultList();
    }

    @Override
    public List<Task> findByUserAndCategory(User user, Long categoryId) {
        return em.createQuery("SELECT t FROM Task t WHERE t.user = :user AND t.category.id = :categoryId", Task.class)
                 .setParameter("user", user)
                 .setParameter("categoryId", categoryId)
                 .getResultList();
    }

    @Override
    public Task update(Task task) {
        return em.merge(task);
    }

    @Override
    public void delete(Task task) {
        em.remove(em.contains(task) ? task : em.merge(task));
    }

    @Override
    public List<Task> findTasksDueBefore(LocalDateTime dateTime) {
        return em.createQuery("SELECT t FROM Task t WHERE t.dueDate <= :dateTime AND t.status = :status", Task.class)
                 .setParameter("dateTime", dateTime)
                 .setParameter("status", Task.Status.ACTIVE)
                 .getResultList();
    }
}
