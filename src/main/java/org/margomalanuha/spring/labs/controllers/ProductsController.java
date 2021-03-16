package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.margomalanuha.spring.labs.service.ProductsServiceImpl;

import java.util.List;

@AllArgsConstructor
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController() {
        productsService = new ProductsServiceImpl();
    }

    public List<Product> getProductsByCatalog(Catalog catalog) { return productsService.getProductsByCatalog(catalog);}
    public List<Product> getAllProducts() { return productsService.getAllProducts(); }
    public Product getProductById(int id) { return productsService.getProductById(id);}

}
