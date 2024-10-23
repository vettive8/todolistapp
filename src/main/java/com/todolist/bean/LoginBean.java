package com.todolist.bean;

import com.todolist.service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    private UserService userService;

    private String username;
    private String password;

    public String login() {
        if (userService.authenticateUser(username, password)) {
            // Set the user in the session
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", username);
            return "dashboard?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true";
    }

    // Getters and setters for username and password
    // ...
}
