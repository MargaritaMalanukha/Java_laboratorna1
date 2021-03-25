package org.margomalanuha.spring.labs.controllers;

import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductsController {

    private final ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    public List<Product> getProductsByCatalog(Catalog catalog) { return productsService.getProductsByCatalog(catalog);}
    public List<Product> getAllProducts() { return productsService.getAllProducts(); }
    public Product getProductById(int id) { return productsService.getProductById(id);}

}
