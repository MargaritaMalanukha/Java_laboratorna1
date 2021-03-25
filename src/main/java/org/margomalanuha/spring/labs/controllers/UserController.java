package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.UserService;
import org.margomalanuha.spring.labs.service.UserServiceImpl;

@AllArgsConstructor
public class UserController {

    private final UserService userService;

    public UserController() {
        userService = new UserServiceImpl();
    }

    public User register(String name, String surname, String email, String password) {
        return userService.register(name, surname, email, password);
    }
    public void updateData(User user) {
        userService.updateData(user);
    }

}
