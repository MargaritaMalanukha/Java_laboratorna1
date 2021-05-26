package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private ProductsService productsService;
    private UserService userService;

    @Autowired
    public void setProductsService(ProductsService productsService) { this.productsService = productsService; }
    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    @GetMapping("/createProduct")
    public void createProduct(String title, double price, Catalog catalog) { productsService.createProduct(title, price, catalog); }
    @GetMapping("/deleteProduct")
    public void deleteProduct(Product product) { productsService.deleteProduct(product); }
    @GetMapping("/updateProduct")
    public void updateProduct(Product product) { productsService.updateProduct(product); }
    @GetMapping("/deactivateUser")
    public void deactivateUser(User user) { userService.deactivateUser(user); }

}
