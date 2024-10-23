package com.todolist.dao.jpa;

import com.todolist.dao.UserDao;
import com.todolist.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class JpaUserDao implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User create(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                 .setParameter("username", username)
                 .getResultStream()
                 .findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                 .setParameter("email", email)
                 .getResultStream()
                 .findFirst();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User update(User user) {
        return em.merge(user);
    }

    @Override
    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }
}
