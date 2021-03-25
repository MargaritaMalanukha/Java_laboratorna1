package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.*;

@AllArgsConstructor
public class AdminController {

    private final ProductsService productsService;
    private final UserService userService;


    public AdminController() {
        productsService = new ProductsServiceImpl();
        userService = new UserServiceImpl();
    }

    public void createProduct(String title, double price, Catalog catalog) { productsService.createProduct(title, price, catalog); }
    public void deleteProduct(Product product) { productsService.deleteProduct(product); }
    public void updateProduct(Product product) { productsService.updateProduct(product); }
    public void deactivateUser(User user) { userService.deactivateUser(user); }

}
