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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceImplTest {

    private ProductRepository repository;
    private ProductsServiceImpl productsService;

    @Before
    public void mock() {
        repository = Mockito.mock(ProductRepository.class);
        productsService = new ProductsServiceImpl(repository);
    }

    @Test
    public void getAllProducts_whenProductsAreInDatabase_ReturnProducts() {
        //Given
        List<Product> products = new LinkedList<>();
        Catalog catalog = new Catalog();
        catalog.setId(1);
        products.add(new Product("milk", 25, catalog));
        catalog = new Catalog();
        catalog.setId(2);
        products.add(new Product("coffee", 100, catalog));
        Mockito.doReturn(products).when(repository).findAll();

        //When
        List<Product> actual = productsService.getAllProducts();

        //Then
        Mockito.verify(repository, Mockito.times(1)).findAll();
        Assertions.assertEquals(products, actual);
    }

    @Test
    public void getProductsByCatalog_whenCatalogExistsAndHasProducts_ReturnProducts() {
        //GIVEN
        List<Product> products = new LinkedList<>();
        Catalog catalog = new Catalog();
        catalog.setId(1);
        products.add(new Product("milk", 25, catalog));
        products.add(new Product("kefir", 30, catalog));
        Catalog catalog2 = new Catalog();
        catalog2.setId(2);
        products.add(new Product("tea", 20, catalog2));
        Mockito.doReturn(products).when(repository).findAll();

        //WHEN
        List<Product> actual = productsService.getProductsByCatalog(1);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findAll();
        products.remove(2);
        Assertions.assertEquals(products, actual);

    }

    @Test
    public void getProductsById_whenProductIsInDatabase_ReturnProduct() {
        //GIVEN
        int productId = 10;
        Catalog catalog = new Catalog();
        catalog.setId(1);
        Product product = new Product("milk", 25, catalog);
        product.setId(productId);
        Optional<Product> optionalProduct = Optional.of(product);
        Mockito.doReturn(optionalProduct).when(repository).findById(productId);

        //WHEN
        Product actual = productsService.getProductById(productId);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findById(productId);
        Assertions.assertEquals(product, actual);
    }

    @Test
    public void getProductsByTitle_whenTitleExists_ReturnProducts() {
        //GIVEN
        String title = "milk";
        List<Product> products = new LinkedList<>();
        Catalog catalog = new Catalog();
        catalog.setId(1);
        products.add(new Product("milk", 25, catalog));
        products.add(new Product("kefir", 30, catalog));
        products.add(new Product("milk 1.5%", 35, catalog));
        Mockito.doReturn(products).when(repository).findAll();

        //WHEN
        List<Product> actual = productsService.getProductsByTitle(title);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findAll();
        products.remove(1);
        Assertions.assertEquals(products, actual);

    }

    @Test
    public void filterByMaxPrice_whenProductsAreInDatabase_ReturnProducts() {
        //GIVEN
        double price = 50.0;
        List<Product> products = new LinkedList<>();
        Catalog catalog = new Catalog();
        catalog.setId(1);
        Catalog catalog2 = new Catalog();
        catalog.setId(3);
        products.add(new Product("salmon", 100, catalog));
        products.add(new Product("milk", 25, catalog2));
        products.add(new Product("ice cream", 50, catalog2));
        products.add(new Product("candies", 47, catalog));
        Mockito.doReturn(products).when(repository).findAll();

        //WHEN
        List<Product> actual = productsService.filterByMaxPrice(price);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findAll();
        products.remove(0);
        Assertions.assertEquals(products, actual);

    }

    @Test
    public void filterByMaxPriceAndCatalog_whenProductsAreInDatabaseAndCatalogExists_ReturnProducts() {
        //GIVEN
        double price = 50.0;
        Catalog primary = new Catalog();
        primary.setId(1);
        Catalog catalog = new Catalog(2, "milk products", primary);
        Catalog another = new Catalog();
        another.setId(3);
        List<Product> products = new LinkedList<>();
        products.add(new Product("salmon", 100,another));
        products.add(new Product("milk", 25, catalog));
        products.add(new Product("milk 2l", 60, catalog));
        products.add(new Product("candies", 47, another));
        Mockito.doReturn(products).when(repository).findAll();

        //WHEN
        List<Product> actual = productsService.filterByMaxPriceAndCatalog(price, catalog.getId());

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findAll();
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
        Product product = new Product(title, price, catalog);
        Mockito.doReturn(product).when(repository).save(product);

        //WHEN
        productsService.createProduct(title, price, catalog.getId());

        //THEN
        Mockito.verify(repository, Mockito.times(1)).save(product);
    }

    @Test
    public void updateProduct_whenDataIsCorrect_updateProductInDatabase() {
        //GIVEN
        Catalog catalog = new Catalog();
        catalog.setId(1);
        Product product = new Product("milk", 25, catalog);
        Mockito.doReturn(product).when(repository).save(product);

        //WHEN
        productsService.updateProduct(product);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).save(product);

    }

    @Test
    public void deleteProduct_whenProductIsInDatabase_deleteProduct() {
        //GIVEN
        Product product = new Product("milk", 25, new Catalog());
        Mockito.doNothing().when(repository).delete(product);

        //WHEN
        productsService.deleteProduct(product);

        //THEN
        Mockito.verify(repository, Mockito.times(1)).delete(product);

    }

    @Test
    public void findProductByTitle_whenProductExists_returnFirstSuchProductFromDatabase() {
        //GIVEN
        List<Product> products = new LinkedList<>();
        products.add(new Product("Milk", 25, new Catalog()));
        products.add(new Product("soda", 12, new Catalog()));
        products.add(new Product("milk", 30, new Catalog()));
        Mockito.doReturn(products).when(repository).findAll();

        //WHEN
        Product product = productsService.findProductByTitle("milk");

        //THEN
        Mockito.verify(repository, Mockito.times(1)).findAll();
        Assertions.assertEquals(products.get(0), product);
    }



}
