package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.*;

import java.util.List;

//todo возвращение товара (бухгалтерия?)

@AllArgsConstructor
public class AdminController {

    private final ProductsService productsService;
    private final UserService userService;
    private final CatalogService catalogService;

    public AdminController() {
        productsService = new ProductsServiceImpl();
        userService = new UserServiceImpl();
        catalogService = new CatalogServiceImpl();
    }

    public void createProduct(String title, double price, Catalog catalog) { productsService.createProduct(title, price, catalog); }
    public void deleteProduct(Product product) { productsService.deleteProduct(product); }
    public void updateProduct(Product product) { productsService.updateProduct(product); }
    public void deactivateUser(User user) { userService.deactivateUser(user); }

    public List<Catalog> getAllCatalogs() {
        return catalogService.getAllCatalogs();
    }
    public void createCatalog(String title) {
        catalogService.createCatalog(title);
    }
    public void updateCatalog(Catalog catalog) {
        catalogService.updateCatalog(catalog);
    }
    public void deleteCatalog(int id) {
        catalogService.deleteCatalog(id);
    }

}
