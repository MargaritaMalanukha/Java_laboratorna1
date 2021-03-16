package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;

import java.util.List;

public interface PurchaseService {

    void addToBasket(Product product, User user);
    void deleteFromBasket(Product product, User user);
    List<Product> getBasket(User user);
    void clearBasket(User user);
    void addPurchaseToHistory(User user);
    List<Purchase> getPurchaseHistory(User user);

}
