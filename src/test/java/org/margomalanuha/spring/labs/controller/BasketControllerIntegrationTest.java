package org.margomalanuha.spring.labs.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.config.Config;
import org.margomalanuha.spring.labs.controllers.BasketController;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.repository.*;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.margomalanuha.spring.labs.service.PurchaseServiceImpl;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest(classes = {Config.class})
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class BasketControllerIntegrationTest {

    private BasketController basketController;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private PurchaseRepository purchaseRepository;

    @Before
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        userRepository = context.getBean(UserRepository.class);
        productRepository = context.getBean(ProductRepository.class);
        purchaseRepository = context.getBean(PurchaseRepository.class);
        BasketItemRepository basketItemRepository = context.getBean(BasketItemRepository.class);
        PurchaseService purchaseService = new PurchaseServiceImpl(basketItemRepository, productRepository, purchaseRepository, userRepository);
        basketController = new BasketController(purchaseService);
    }

    @Test
    public void getNonFinishedPurchases_whenTheyAreInDB() {
        //WHEN
        List<Purchase> purchases = basketController.getAllNonFinishedPurchases();

        //THEN
        AtomicBoolean areNonFinished = new AtomicBoolean(true);
        purchases.forEach(e -> {
            if (e.getStatus().equalsIgnoreCase("finished")) areNonFinished.set(false);
        });
        Assert.assertTrue(areNonFinished.get());
    }

    @Test
    public void makeAnOrder() {
        User user = userRepository.findAll().get(userRepository.findAll().size()-3);
        List<Product> products = productRepository.findAll();

        int beforeSize = basketController.getBasket(user.getId()).size();
        basketController.addToBasket(products.get(5).getId(), user.getId());
        basketController.addToBasket(products.get(3).getId(), user.getId());
        basketController.addToBasket(products.get(2).getId(), user.getId());
        int afterSize = basketController.getBasket(user.getId()).size();

        basketController.deleteFromBasket(products.get(2).getId(), user.getId());
        int afterDeletionSize = basketController.getBasket(user.getId()).size();

        int beforeAddingToHistory = basketController.getPurchaseHistory(user.getId()).size();
        Purchase purchase = basketController.addPurchaseToHistory(user.getId());
        int afterAddingToHistory = basketController.getPurchaseHistory(user.getId()).size();

        basketController.clearBasket(user.getId());
        int afterClearing = basketController.getBasket(user.getId()).size();

        Assert.assertEquals(beforeSize + 3, afterSize);
        Assert.assertEquals(beforeSize + 2, afterDeletionSize);
        Assert.assertEquals(beforeAddingToHistory + 1, afterAddingToHistory);
        Assert.assertEquals(0, afterClearing);

        //TEARDOWN
        purchaseRepository.delete(purchase);
    }

}
