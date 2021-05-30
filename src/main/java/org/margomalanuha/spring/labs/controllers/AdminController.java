package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor
@RestController
@AllArgsConstructor
public class AdminController {

    private ProductsService productsService;
    private UserService userService;

    @Autowired
    public void setProductsService(ProductsService productsService) { this.productsService = productsService; }
    @Autowired
    public void setUserService(UserService userService) { this.userService = userService; }

    public void createProduct(String title, double price, int catalogId) { productsService.createProduct(title, price, catalogId); }
    public void deleteProduct(Product product) { productsService.deleteProduct(product); }
    public void updateProduct(Product product) { productsService.updateProduct(product); }
    public void deactivateUser(Integer userId) { userService.deactivateUser(userId); }

    public void upgradeToAdmin(Integer userId) {
        userService.upgradeToAdmin(userId);
    }

    public void downgradeToUser(Integer userId) {
        userService.downgradeToUser(userId);
    }

}
