package org.margomalanuha.spring.labs.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.margomalanuha.spring.labs.models.pojo.*;
import org.margomalanuha.spring.labs.repository.BasketItemRepository;
import org.margomalanuha.spring.labs.repository.ProductRepository;
import org.margomalanuha.spring.labs.repository.PurchaseRepository;
import org.margomalanuha.spring.labs.repository.UserRepository;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceImplTest {

    private PurchaseRepository purchaseRepository;
    private ProductRepository productRepository;
    private BasketItemRepository basketItemRepository;
    private UserRepository userRepository;
    private PurchaseServiceImpl purchaseService;

    @Before
    public void mock() {
        purchaseRepository = Mockito.mock(PurchaseRepository.class);
        productRepository = Mockito.mock(ProductRepository.class);
        basketItemRepository = Mockito.mock(BasketItemRepository.class);
        userRepository = Mockito.mock(UserRepository.class);

        purchaseService = new PurchaseServiceImpl(basketItemRepository, productRepository, purchaseRepository, userRepository);
    }

    @Test
    public void addToBasket_whenDataIsCorrect_CreateNewRecordInDB() {
        //GIVEN
        User user = new User();
        user.setId(15);
        Product product = new Product();
        product.setId(12);

        BasketItem basketItem = new BasketItem(product, user);
        Mockito.doReturn(basketItem).when(basketItemRepository).save(basketItem);

        //WHEN
        purchaseService.addToBasket(product.getId(), user.getId());

        //THEN
        Mockito.verify(basketItemRepository, Mockito.times(1)).save(new BasketItem(product, user));

    }

    @Test
    public void deleteFromBasket_whenExists_DeleteRecordFromDB() {
        //GIVEN
        List<BasketItem> basketItems = new LinkedList<>();
        User user = new User();
        user.setId(1);
        Product product = new Product();
        product.setId(2);
        Product product1 = new Product();
        product.setId(3);
        basketItems.add(new BasketItem(1, product, user));
        basketItems.add(new BasketItem(2, product, user));
        basketItems.add(new BasketItem(3, product1, user));

        Mockito.doReturn(basketItems).when(basketItemRepository).findAll();
        Mockito.doNothing().when(basketItemRepository).delete(basketItems.get(0));

        //WHEN
        purchaseService.deleteFromBasket(product.getId(), user.getId());

        //THEN
        Mockito.verify(basketItemRepository, Mockito.times(1)).findAll();
        Mockito.verify(basketItemRepository, Mockito.times(1)).delete(basketItems.get(0));
    }

    @Test
    public void getBasket_whenUserExists_returnBasketItemsOfThisUser() {
        //GIVEN
        User user = new User();
        user.setId(1);
        List <BasketItem> basketItems = new LinkedList<>();
        Product product = new Product();
        product.setId(1);
        Product product1 = new Product();
        product1.setId(2);
        Product product2 = new Product();
        product2.setId(5);
        basketItems.add(new BasketItem(1, product, user));
        basketItems.add(new BasketItem(2, product1, user));
        basketItems.add(new BasketItem(3, product2, user));
        basketItems.add(new BasketItem(4, product1, new User()));
        List<Product> products = new LinkedList<>();
        products.add(product);
        products.add(product1);
        products.add(product2);

        Mockito.doReturn(basketItems).when(basketItemRepository).findAll();
        Mockito.doReturn(Optional.of(products.get(0))).when(productRepository).findById(1);
        Mockito.doReturn(Optional.of(products.get(1))).when(productRepository).findById(2);
        Mockito.doReturn(Optional.of(products.get(2))).when(productRepository).findById(5);

        //WHEN
        List<Product> actual = purchaseService.getBasket(user.getId());

        //THEN
        Mockito.verify(basketItemRepository, Mockito.times(1)).findAll();
        Mockito.verify(productRepository, Mockito.times(1)).findById(1);
        Assertions.assertEquals(products, actual);
    }

    @Test
    public void clearBasket_whenNotEmpty_DeleteRecordsFromDatabase() {
        //GIVEN
        List<BasketItem> basketItems = new LinkedList<>();
        User user = new User();
        user.setId(1);
        basketItems.add(new BasketItem(1, new Product(), user));
        basketItems.add(new BasketItem(2, new Product(), user));
        basketItems.add(new BasketItem(3, new Product(), user));
        basketItems.add(new BasketItem(4, new Product(), new User()));
        Mockito.doReturn(basketItems).when(basketItemRepository).findAll();
        Mockito.doNothing().when(basketItemRepository).delete(basketItems.get(0));
        Mockito.doNothing().when(basketItemRepository).delete(basketItems.get(1));
        Mockito.doNothing().when(basketItemRepository).delete(basketItems.get(2));

        //WHEN
        purchaseService.clearBasket(user.getId());

        //THEN
        Mockito.verify(basketItemRepository, Mockito.times(1)).findAll();
        Mockito.verify(basketItemRepository, Mockito.times(1)).delete(basketItems.get(0));
        Mockito.verify(basketItemRepository, Mockito.times(1)).delete(basketItems.get(1));
        Mockito.verify(basketItemRepository, Mockito.times(1)).delete(basketItems.get(2));
    }

    @Test
    public void getPurchaseHistory_whenPurchasesAreInDatabase_returnAllPurchasesForUser() {
        //GIVEN
        User user = new User();
        user.setId(1);
        List<Purchase> purchases = new LinkedList<>();
        purchases.add(new Purchase(user, 200, ""));
        purchases.add(new Purchase(user, 150, ""));
        purchases.add(new Purchase(new User(), 300, ""));
        purchases.add(new Purchase(user, 160,""));
        Mockito.doReturn(purchases).when(purchaseRepository).findAll();

        //WHEN
        List<Purchase> actual = purchaseService.getPurchaseHistory(user.getId());

        //THEN
        Mockito.verify(purchaseRepository, Mockito.times(1)).findAll();
        purchases.remove(2);
        Assertions.assertEquals(purchases, actual);
    }

    @Test
    public void getAllNonFinishedPurchases_returnAllPurchasesWhereStatusIsNotStarted() {
        //GIVEN
        Purchase purchase = new Purchase(new User(), 200, "");
        purchase.setStatus("not started");
        Purchase purchase1 = new Purchase(new User(), 150, "");
        purchase1.setStatus("not started");
        Purchase purchase2 = new Purchase(new User(), 300, "");
        purchase2.setStatus("finished");
        List<Purchase> purchases = new LinkedList<>();
        purchases.add(purchase);
        purchases.add(purchase1);
        purchases.add(purchase2);
        Mockito.doReturn(purchases).when(purchaseRepository).findAll();

        //WHEN
        List<Purchase> actual = purchaseService.getAllNonFinishedPurchases();

        //THEN
        Mockito.verify(purchaseRepository, Mockito.times(1)).findAll();
        purchases.remove(purchase2);
        Assertions.assertEquals(purchases, actual);

    }

}
