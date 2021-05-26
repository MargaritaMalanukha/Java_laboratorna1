package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductsService {

    List<Product> getAllProducts();
    List<Product> getProductsByCatalog(Catalog catalog);
    Product getProductById(int id);
    List<Product> getProductsByTitle(String title);

    List<Product> filterByMaxPrice(double price);
    List<Product> filterByMaxPriceAndCatalog(double price, Catalog catalog);

    void createProduct(String title, double price, Catalog catalog);
    void updateProduct(Product product);
    void deleteProduct(Product product);

}
