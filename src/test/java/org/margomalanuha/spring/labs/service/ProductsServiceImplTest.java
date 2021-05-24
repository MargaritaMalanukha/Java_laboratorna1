package org.margomalanuha.spring.labs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.models.pojo.Catalog;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.repository.ProductRepository;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceImplTest {

    private ProductRepository repository;
    private ProductsServiceImpl productsService;

    @Before
    public void mock() {
        repository = Mockito.mock(ProductRepository.class);
        repository.setTest();
        productsService = new ProductsServiceImpl(repository);
    }

    @Test
    public void getAllProducts_whenProductsAreInDatabase_ReturnProducts() {
        //Given
        List<Product> products = new LinkedList<>();
        products.add(new Product("milk", 25, 1));
        products.add(new Product("coffee", 100, 2));
        Mockito.doReturn(products).when(repository).getAll();

        //When
        List<Product> actual = productsService.getAllProducts();

        //Then
        Mockito.verify(repository, Mockito.times(1)).getAll();
        Assertions.assertEquals(products, actual);
    }

    @Test
    public void getProductsByCatalog_whenCatalogExistsAndHasProducts_ReturnProducts() {
        //GIVEN
        List<Product> products = new LinkedList<>();
        products.add(new Product("milk", 25, 1));
        products.add(new Product("kefir", 30, 1));
        products.add(new Product("tea", 20, 2));
        Mockito.doReturn(products).when(repository).getAll();
        Catalog catalog = new Catalog(1, "milk products", 0);

        //WHEN
        List<Product> actual = productsService.getProductsByCatalog(catalog);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).getAll();
        products.remove(2);
        Assertions.assertEquals(products, actual);

    }

    @Test
    public void getProductsById_whenProductIsInDatabase_ReturnProduct() {
        //GIVEN
        int productId = 10;
        Product product = new Product(productId,"milk", 25, 1);
        Mockito.doReturn(product).when(repository).getById(productId);

        //WHEN
        Product actual = productsService.getProductById(productId);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).getById(productId);
        Assertions.assertEquals(product, actual);
    }

    @Test
    public void getProductsByTitle_whenTitleExists_ReturnProducts() {
        //GIVEN
        String title = "milk";
        List<Product> products = new LinkedList<>();
        products.add(new Product("milk", 25, 1));
        products.add(new Product("kefir", 30, 1));
        products.add(new Product("milk 1.5%", 35, 1));
        Mockito.doReturn(products).when(repository).getAll();

        //WHEN
        List<Product> actual = productsService.getProductsByTitle(title);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).getAll();
        products.remove(1);
        Assertions.assertEquals(products, actual);

    }

    @Test
    public void filterByMaxPrice_whenProductsAreInDatabase_ReturnProducts() {
        //GIVEN
        double price = 50.0;
        List<Product> products = new LinkedList<>();
        products.add(new Product("salmon", 100,3));
        products.add(new Product("milk", 25, 1));
        products.add(new Product("ice cream", 50, 4));
        products.add(new Product("candies", 47, 4));
        Mockito.doReturn(products).when(repository).getAll();

        //WHEN
        List<Product> actual = productsService.filterByMaxPrice(price);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).getAll();
        products.remove(0);
        Assertions.assertEquals(products, actual);

    }

    @Test
    public void filterByMaxPriceAndCatalog_whenProductsAreInDatabaseAndCatalogExists_ReturnProducts() {
        //GIVEN
        double price = 50.0;
        Catalog catalog = new Catalog(1, "milk products", 0);
        List<Product> products = new LinkedList<>();
        products.add(new Product("salmon", 100,3));
        products.add(new Product("milk", 25, 1));
        products.add(new Product("milk 2l", 60, 1));
        products.add(new Product("candies", 47, 4));
        Mockito.doReturn(products).when(repository).getAll();

        //WHEN
        List<Product> actual = productsService.filterByMaxPriceAndCatalog(price, catalog);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).getAll();
        products.remove(3);
        products.remove(2);
        products.remove(0);
        Assertions.assertEquals(products, actual);
    }

    @Test
    public void createProduct_whenDataIsCorrect_createNewProductInDatabase() {
        //GIVEN
        double price = 25;
        String title = "milk";
        Catalog catalog = new Catalog();
        catalog.setId(1);
        Product product = new Product(title, price, catalog.getId());
        Mockito.doReturn(1).when(repository).create(product);

        //WHEN
        productsService.createProduct(title, price, catalog);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).create(product);
    }

    @Test
    public void updateProduct_whenDataIsCorrect_updateProductInDatabase() {
        //GIVEN
        Product product = new Product(12, "milk", 25, 1);
        Mockito.doReturn(1).when(repository).create(product);

        //WHEN
        productsService.updateProduct(product);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).update(product);

    }

    @Test
    public void deleteProduct_whenProductIsInDatabase_deleteProduct() {
        //GIVEN
        Product product = new Product(12, "milk", 25, 1);
        Mockito.doReturn(1).when(repository).delete(product.getId());

        //WHEN
        productsService.deleteProduct(product);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).delete(product.getId());

    }



}
