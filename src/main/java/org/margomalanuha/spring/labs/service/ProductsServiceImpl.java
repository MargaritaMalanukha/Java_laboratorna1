package org.margomalanuha.spring.labs.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.repository.CatalogRepository;
import org.margomalanuha.spring.labs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Getter
public class ProductsServiceImpl implements ProductsService {

    private ProductRepository repository;
    private CatalogRepository catalogRepository;

    @Autowired
    public ProductsServiceImpl(ProductRepository repository, CatalogRepository catalogRepository) {
        this.repository = repository;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public List<Product> getProductsByCatalog(int catalogId) {
        return repository.findAll().stream().filter(e -> e.getCatalog().getId() == catalogId).collect(Collectors.toList());
    }

    @Override
    public Product getProductById(int id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Product> getProductsByTitle(String title) {
        return repository.findAll().stream().filter(e -> e.getTitle().contains(title)).collect(Collectors.toList());
    }

    @Override
    public List<Product> filterByMaxPrice(double price) {
        return repository.findAll().stream().filter(e -> e.getPrice() <= price).collect(Collectors.toList());
    }

    @Override
    public List<Product> filterByMaxPriceAndCatalog(double price, int catalogId) {
        List<Product> list = getProductsByCatalog(catalogId);
        return list.stream().filter(e -> e.getPrice() <= price).collect(Collectors.toList());
    }

    @Override
    public void createProduct(String title, double price, int catalogId) {
        Catalog catalog = catalogRepository.findById(catalogId).orElseThrow(NoSuchElementException::new);
        repository.save(new Product(title, price, catalog));
    }

    @Override
    public void updateProduct(Product product) {
        repository.save(product);
    }

    @Override
    public void deleteProduct(Integer integer) {
        repository.delete(repository.findById(integer).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public Product findProductByTitle(String title) {
        return repository.findAll().stream()
                .filter(e -> e.getTitle().equalsIgnoreCase(title))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }


}
