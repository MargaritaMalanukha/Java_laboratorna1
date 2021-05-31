package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@NoArgsConstructor
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @PostMapping("/deactivate")
    public void deactivateUser(@RequestParam Integer userId) { userService.deactivateUser(userId); }

    @PostMapping("/upgrade")
    public void upgradeToAdmin(@RequestParam Integer userId) {
        userService.upgradeToAdmin(userId);
    }

    @PostMapping("/downgrade")
    public void downgradeToUser(@RequestParam Integer userId) {
        userService.downgradeToUser(userId);
    }

}
