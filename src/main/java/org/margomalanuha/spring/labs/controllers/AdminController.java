package org.margomalanuha.spring.labs.controllers;

import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {

    private ProductsService productsService;
    private UserService userService;

    @Autowired
    public void setProductsService(ProductsService productsService) { this.productsService = productsService; }
    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    public void createProduct(String title, double price, Catalog catalog) { productsService.createProduct(title, price, catalog); }
    public void deleteProduct(Product product) { productsService.deleteProduct(product); }
    public void updateProduct(Product product) { productsService.updateProduct(product); }
    public void deactivateUser(User user) { userService.deactivateUser(user); }

}
