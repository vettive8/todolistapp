package com.todolist.bean;

import com.todolist.model.User;
import com.todolist.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
@ViewScoped
public class UserRegistrationBean {

    @Inject
    private UserService userService;

    private User user = new User();

    public String register() {
        try {
            userService.registerUser(user);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful", null));
            return "login?faces-redirect=true";
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registration failed: " + e.getMessage(), null));
            return null;
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
