package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PurchaseService {

    void addToBasket(int productId, int userId);
    void deleteFromBasket(int productId, int userId);
    List<Product> getBasket(int userId);
    void clearBasket(int userId);
    Purchase addPurchaseToHistory(int userId);
    List<Purchase> getPurchaseHistory(int userId);
    List<Purchase> getAllNonFinishedPurchases();
    void setPurchaseAsFinished(Purchase purchase);

}
