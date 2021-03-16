package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.margomalanuha.spring.labs.service.ProductsServiceImpl;

import java.util.List;

@AllArgsConstructor
public class SearchController {

    private final ProductsService productsService;

    public SearchController() {
        productsService = new ProductsServiceImpl();
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
