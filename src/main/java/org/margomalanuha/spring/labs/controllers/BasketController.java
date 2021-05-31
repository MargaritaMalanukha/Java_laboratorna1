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
@RequestMapping("/api/basket")
public class BasketController {

    private PurchaseService purchaseService;

    @Autowired
    public BasketController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public void addToBasket(Integer productId, Integer userId) {
        purchaseService.addToBasket(productId, userId);
    }

    @DeleteMapping("/{productId}")
    public void deleteFromBasket(@PathVariable Integer productId, @RequestParam Integer userId) {
        purchaseService.deleteFromBasket(productId, userId);
    }

    @GetMapping
    public List<Product> getBasket(Integer userId) {
        return purchaseService.getBasket(userId);
    }

    @GetMapping("/clear")
    public void clearBasket(Integer userId) {
        purchaseService.clearBasket(userId);
    }

    @PostMapping("/history")
    public Purchase addPurchaseToHistory(Integer userId) {
        return purchaseService.addPurchaseToHistory(userId);
    }

    @GetMapping("/history")
    public List<Purchase> getPurchaseHistory(Integer userId) {
        return purchaseService.getPurchaseHistory(userId);
    }

    @GetMapping("/all-purchases")
    public List<Purchase> getAllNonFinishedPurchases() {
        return purchaseService.getAllNonFinishedPurchases();
    }
}
