package org.margomalanuha.spring.labs.controllers;

import lombok.AllArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.margomalanuha.spring.labs.service.PurchaseServiceImpl;

import java.util.List;

@AllArgsConstructor
public class BasketController {

    private final PurchaseService purchaseService;

    public BasketController() {
        purchaseService = new PurchaseServiceImpl();
    }

    public void addToBasket(Product product, User user) {
        purchaseService.addToBasket(product, user);
    }
    public void deleteFromBasket(Product product, User user) {
        purchaseService.deleteFromBasket(product, user);
    }
    public List<Product> getBasket(User user) {
        return purchaseService.getBasket(user);
    }
    public void clearBasket(User user) {
        purchaseService.clearBasket(user);
    }
    public void addPurchaseToHistory(User user) {
        purchaseService.addPurchaseToHistory(user);
    }
    public List<Purchase> getPurchaseHistory(User user) {
        return purchaseService.getPurchaseHistory(user);
    }
}
