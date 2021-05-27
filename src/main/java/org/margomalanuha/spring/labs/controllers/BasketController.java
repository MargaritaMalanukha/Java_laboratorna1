package org.margomalanuha.spring.labs.controllers;

import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@NoArgsConstructor
@RestController
public class BasketController {

    private PurchaseService purchaseService;

    @Autowired
    public BasketController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
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
    public double returnPriceByCheque(String cheque, Product product) { return purchaseService.returnPriceByCheque(cheque, product); }
}
