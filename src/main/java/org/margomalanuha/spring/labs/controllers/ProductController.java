package org.margomalanuha.spring.labs.controllers;

import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@RestController
public class ProductController {

    private ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    public List<Product> getProductsByCatalog(Integer catalogId) {
        List<Product> products;
        try {
            products = productsService.getProductsByCatalog(catalogId);
        } catch (Exception e) {
            products = new LinkedList<>();
        }
        return products;
    }

    public List<Product> getAllProducts() {
        return productsService.getAllProducts();
    }

    public Product getProductById(Integer id) {
        return productsService.getProductById(id);
    }

    public Product getProductByTitle(String title) {
        return productsService.findProductByTitle(title);
    }

}
