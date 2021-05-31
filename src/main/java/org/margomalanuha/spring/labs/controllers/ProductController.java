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
@RequestMapping("/api/products")
public class ProductController {

    private ProductsService productsService;

    @Autowired
    public ProductController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/catalog/{catalogId}")
    public List<Product> getProductsByCatalog(@PathVariable Integer catalogId) {
        List<Product> products;
        try {
            products = productsService.getProductsByCatalog(catalogId);
        } catch (Exception e) {
            products = new LinkedList<>();
        }
        return products;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productsService.getProductById(id);
    }

    @GetMapping("/title/{title}")
    public Product getProductByTitle(@PathVariable String title) {
        return productsService.findProductByTitle(title);
    }

    @PostMapping
    public void createProduct(@RequestParam String title,
                              @RequestParam double price,
                              @RequestParam int catalogId) {
        productsService.createProduct(title, price, catalogId);
    }

    @DeleteMapping("/{productId}")
    public void deleteProduct(@PathVariable Integer productId) { productsService.deleteProduct(productId); }

    @PutMapping
    public void updateProduct(@RequestBody Product product) { productsService.updateProduct(product); }

}
