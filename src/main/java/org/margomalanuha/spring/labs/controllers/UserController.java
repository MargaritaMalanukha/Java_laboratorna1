package org.margomalanuha.spring.labs.controllers;

import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User register(String name, String surname, String email, String password) {
        return userService.register(name, surname, email, password);
    }
    public void updateData(User user) {
        userService.updateData(user);
    }

}
