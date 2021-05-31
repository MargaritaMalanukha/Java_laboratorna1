package org.margomalanuha.spring.labs.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.config.Config;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration( loader = AnnotationConfigWebContextLoader.class, classes = {Config.class})
@Transactional //todo
public class ProductAndAdminControllerIntegrationTest {

  /*  private static ApplicationContext context;
    private AdminController adminController;
    private ProductsController productsController;

    @Before
    public void init() {
        context = new AnnotationConfigApplicationContext(Config.class);
        adminController = context.getBean(AdminController.class);
        productsController = context.getBean(ProductsController.class);
    }

    @Test
    public void createProduct_whenDataIsCorrect_createProductInDatabase() {
        //GIVEN
        String title = "Milk";
        double price = 27.0;
        Catalog catalog = new Catalog();
        catalog.setId(1);

        //WHEN
        adminController.createProduct(title, price, catalog);

        //THEN
        List<Product> products = productsController.getAllProducts();
        Product product = products.get(products.size() - 1);
        Assertions.assertEquals(title, product.getTitle());
    }

    @Test
    public void updateProduct_whenDataIsCorrect_updateProductInDatabase() {
        //GIVEN
        Product product = productsController.getAllProducts().get(productsController.getAllProducts().size()-1);
        product.setTitle("Milk 3.2%");

        //WHEN
        adminController.updateProduct(product);

        //THEN
        Product actual = productsController.getAllProducts().get(productsController.getAllProducts().size()-1);
        Assertions.assertEquals(product.getTitle(), actual.getTitle());
    }

    @Test
    public void deleteProduct_whenDataIsCorrect_removeProductFromDatabase() {
        //GIVEN
        int prevSize = productsController.getAllProducts().size();
        Product product = productsController.getAllProducts().get(prevSize-1);

        //WHEN
        adminController.deleteProduct(product);

        //THEN
        int currentSize = productsController.getAllProducts().size();
        Assertions.assertEquals(prevSize + 1, currentSize);
    }

    @Test
    public void getProductById_getProductWithGivenIdFromDB() {
        //GIVEN
        int id = productsController.getAllProducts().get(productsController.getAllProducts().size()-1).getId();
        System.out.println(id);

        //WHEN
        Product product = productsController.getProductById(id);

        //THEN
        Assertions.assertNotNull(product);
        Assertions.assertEquals(id, product.getId());

    }*/






}
