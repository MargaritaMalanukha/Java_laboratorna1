package org.margomalanuha.spring.labs.controllers;

import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SearchController {

    private final ProductsService productsService;

    @Autowired
    public SearchController(ProductsService productsService) {
        this.productsService = productsService;
    }

    public List<Product> searchByTitle(String title) {
        return productsService.getProductsByTitle(title);
    }
    public List<Product> filterByMaxPrice(double price) {
        return productsService.filterByMaxPrice(price);
    }
    public List<Product> filterByMaxPriceAndCatalog(double price, Catalog catalog) {
        return productsService.filterByMaxPriceAndCatalog(price, catalog);
    }

}
