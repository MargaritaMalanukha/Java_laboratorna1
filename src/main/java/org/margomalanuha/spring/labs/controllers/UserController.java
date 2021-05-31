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
@RequestMapping("api/auth")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        userService.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestParam String email, @RequestParam String password) {
        return userService.login(email, password);
    }

    @PostMapping("/update")
    public void updateData(@RequestBody User user) {
        userService.updateData(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/users/title/{title}")
    public List<User> getUsersByTitle(@PathVariable String title) {
        return userService.getUsersByTitle(title);
    }

    @PostMapping("/is_main_admin")
    public boolean isMainAdmin(@RequestBody User user) {
        return userService.isMainAdmin(user);
    }

    @PostMapping("/is_admin")
    public boolean isAdmin(@RequestBody User user) { return userService.isAdmin(user);}
}
