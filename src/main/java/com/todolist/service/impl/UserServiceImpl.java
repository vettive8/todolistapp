package com.todolist.service.impl;

import com.todolist.dao.UserDao;
import com.todolist.model.User;
import com.todolist.service.UserService;
import com.todolist.util.PasswordHasher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public User registerUser(User user) throws Exception {
        if (userDao.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            throw new Exception("Email already exists");
        }
        user.setPassword(PasswordHasher.hashPassword(user.getPassword()));
        return userDao.create(user);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User updateUser(User user) throws Exception {
        // TODO: Implement update logic, including password hashing if changed
        return userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        Optional<User> userOpt = userDao.findByUsername(username);
        if (userOpt.isPresent()) {
            try {
                return PasswordHasher.verifyPassword(password, userOpt.get().getPassword());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                // Log the error and return false
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
