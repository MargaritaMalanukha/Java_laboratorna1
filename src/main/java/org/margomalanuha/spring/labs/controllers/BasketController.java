package org.margomalanuha.spring.labs.controllers;

import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/basket")
public class BasketController {

    private PurchaseService purchaseService;

    @Autowired
    public BasketController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @GetMapping("/addToBasket")
    public void addToBasket(Product product, User user) {
        purchaseService.addToBasket(product, user);
    }
    @GetMapping("/deleteFromBasket")
    public void deleteFromBasket(Product product, User user) {
        purchaseService.deleteFromBasket(product, user);
    }
    @GetMapping("/getBasket")
    public List<Product> getBasket(User user) {
        return purchaseService.getBasket(user);
    }
    @GetMapping("/clearBasket")
    public void clearBasket(User user) {
        purchaseService.clearBasket(user);
    }
    @GetMapping("/addPurchaseToHistory")
    public void addPurchaseToHistory(User user) {
        purchaseService.addPurchaseToHistory(user);
    }
    @GetMapping("/getPurchaseHistory")
    public List<Purchase> getPurchaseHistory(User user) {
        return purchaseService.getPurchaseHistory(user);
    }

    @GetMapping("/pricing")
    public double returnPriceByCheque(String cheque, Product product) { return purchaseService.returnPriceByCheque(cheque, product); }
}
