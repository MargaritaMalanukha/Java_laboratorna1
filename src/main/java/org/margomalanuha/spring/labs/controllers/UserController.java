package org.margomalanuha.spring.labs.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.controllers.exceptions.LoginException;
import org.margomalanuha.spring.labs.controllers.exceptions.RegistrationException;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@NoArgsConstructor
@RestController
@Getter
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User register(User user) {
        if (user.getName() == null || user.getSurname() == null || user.getEmail() == null || user.getPassword() == null) {
            throw new RegistrationException("Wrong data for registration!");
        }
        if (userService.getAllUsers().stream().anyMatch(e -> e.getEmail().equals(user.getEmail()))) {
            throw new RegistrationException("User with this email already exists!");
        }
        return userService.register(user);
    }
    public User login(String email, String password) {
        if (email == null || password == null) {
            throw new LoginException("Wrong data for login!");
        }
        return userService.login(email, password);
    }

    public void updateData(User user) {
        userService.updateData(user);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
