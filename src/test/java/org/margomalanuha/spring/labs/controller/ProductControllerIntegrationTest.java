package org.margomalanuha.spring.labs.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.controllers.ProductController;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.repository.CatalogRepository;
import org.margomalanuha.spring.labs.repository.ProductRepository;
import org.margomalanuha.spring.labs.service.ProductsService;
import org.margomalanuha.spring.labs.service.ProductsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest(classes = {Config.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class ProductControllerIntegrationTest {

    private ProductController productController;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);
        CatalogRepository catalogRepository = context.getBean(CatalogRepository.class);
        ProductsService productsService = new ProductsServiceImpl(productRepository, catalogRepository);
        productController = new ProductController(productsService);
    }

    @Test
    public void deleteProduct_whenIdIsCorrect_deleteProduct() {
        //GIVEN
        int productId = productController.getAllProducts().get(productController.getAllProducts().size()-1).getId();

        //WHEN
        int beforeSize = productController.getAllProducts().size();
        productController.deleteProduct(productId);
        int afterSize = productController.getAllProducts().size();

        //THEN
        Assert.assertEquals(beforeSize, afterSize + 1);
    }

    @Test
    public void updateProduct_whenDataIsCorrect_updateProduct() {
        //GIVEN
        Product product = productController.getAllProducts().get(productController.getAllProducts().size()-1);
        product.setPrice(30.0);

        //WHEN
        productController.updateProduct(product);

        //THEN
        Product actual = productController.getAllProducts().get(productController.getAllProducts().size()-1);
        Assert.assertEquals(product.getTitle(), actual.getTitle());
    }

    @Test
    public void createProduct_whenDataIsCorrect_addNewProductToDB() {
        //GIVEN
        String title = "Banana";
        double price = 29.0;
        int catalogId = 2;

        //WHEN
        int beforeSize = productController.getAllProducts().size();
        productController.createProduct(title, price, catalogId);
        int afterSize = productController.getAllProducts().size();

        //THEN
        Assert.assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void getProductByTitle_whenProductExists_returnProduct(){
        //GIVEN
        String title = "Salmon";

        //WHEN
        Product product = productController.getProductByTitle(title);

        //THEN
        Assert.assertEquals(title, product.getTitle());
    }

    @Test
    public void getProductById_ifProductExists_returnProduct() {
        //GIVEN
        int productId = 1;

        //WHEN
        Product product = productController.getProductById(productId);

        //THEN
        Assert.assertEquals(productId, product.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void getProductById_ifProductWithSuchIdNotExists_throwNoSuchElementException() {
        int productId = 0;
        productController.getProductById(productId);
    }

    @Test
    public void getProductsByCatalog_ifCatalogIsNonEmpty_returnProducts() {
        //GIVEN
        int catalogId = 1;
        List<Product> allProducts = productController.getAllProducts()
                .stream()
                .filter(e -> e.getCatalog().getId() == catalogId)
                .collect(Collectors.toList());

        //WHEN
        List<Product> actual = productController.getProductsByCatalog(catalogId);

        //THEN
        Assert.assertEquals(allProducts.size(), actual.size());
    }


}
