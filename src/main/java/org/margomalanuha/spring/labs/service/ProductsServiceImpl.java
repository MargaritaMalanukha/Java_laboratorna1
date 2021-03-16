package org.margomalanuha.spring.labs.service;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private final ProductRepository repository;

    public ProductsServiceImpl() {
        repository = new ProductRepository();
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.getAll();
    }

    @Override
    public List<Product> getProductsByCatalog(Catalog catalog) {
        int catalogId = catalog.getId();
        return repository.getAll().stream().filter(e -> e.getCatalogId() == catalogId).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Product> getProductsByTitle(String title) {
        return repository.getAll().stream().filter(e -> e.getTitle().contains(title)).collect(Collectors.toList());
    }

    @Override
    public List<Product> filterByMaxPrice(double price) {
        return repository.getAll().stream().filter(e -> e.getPrice() < price).collect(Collectors.toList());
    }

    @Override
    public List<Product> filterByMaxPriceAndCatalog(double price, Catalog catalog) {
        List<Product> list = getProductsByCatalog(catalog);
        return list.stream().filter(e -> e.getPrice() < price).collect(Collectors.toList());
    }

    @Override
    public void createProduct(String title, double price, Catalog catalog) {
        repository.create(new Product(title, price, catalog.getId()));
    }

    @Override
    public void updateProduct(Product product) {
        repository.update(product);
    }

    @Override
    public void deleteProduct(Product product) {
        repository.delete(product.getId());
    }
}
