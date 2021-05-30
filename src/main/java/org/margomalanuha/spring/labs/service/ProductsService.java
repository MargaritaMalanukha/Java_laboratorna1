package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public interface ProductsService {

    List<Product> getAllProducts();
    List<Product> getProductsByCatalog(int catalogId);
    Product getProductById(int id);
    List<Product> getProductsByTitle(String title);

    List<Product> filterByMaxPrice(double price);
    List<Product> filterByMaxPriceAndCatalog(double price, int catalogId);

    void createProduct(String title, double price, int catalogId);
    void updateProduct(Product product);
    void deleteProduct(Product product);
    Product findProductByTitle(String title);

}
