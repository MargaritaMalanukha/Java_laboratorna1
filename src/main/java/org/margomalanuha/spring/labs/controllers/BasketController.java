package org.margomalanuha.spring.labs.controllers;

import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
public class BasketController {

    private PurchaseService purchaseService;

    @Autowired
    public BasketController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public void addToBasket(Integer productId, Integer userId) {
        purchaseService.addToBasket(productId, userId);
    }

    public void deleteFromBasket(Integer productId, Integer userId) {
        purchaseService.deleteFromBasket(productId, userId);
    }

    public List<Product> getBasket(Integer userId) {
        return purchaseService.getBasket(userId);
    }

    public void clearBasket(Integer userId) {
        purchaseService.clearBasket(userId);
    }

    public Purchase addPurchaseToHistory(Integer userId) {
        return purchaseService.addPurchaseToHistory(userId);
    }

    public List<Purchase> getPurchaseHistory(Integer userId) {
        return purchaseService.getPurchaseHistory(userId);
    }

    public List<Purchase> getAllNonFinishedPurchases() {
        return purchaseService.getAllNonFinishedPurchases();
    }
}
