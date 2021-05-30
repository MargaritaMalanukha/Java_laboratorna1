package org.margomalanuha.spring.labs.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@NoArgsConstructor
@RestController
@Getter
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    public void register(User user) {
        userService.register(user);
    }

    public User login(String email, String password) {
        return userService.login(email, password);
    }

    public void updateData(User user) {
        userService.updateData(user);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public List<User> getUsersByTitle(String title) {
        return userService.getUsersByTitle(title);
    }

    public boolean isMainAdmin(User user) {
        return userService.isMainAdmin(user);
    }

    public boolean isAdmin(User user) { return userService.isAdmin(user);}
}
