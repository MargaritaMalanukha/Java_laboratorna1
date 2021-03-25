package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.BasketItem;
import org.margomalanuha.spring.labs.models.pojo.Product;
import org.margomalanuha.spring.labs.models.pojo.Purchase;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.repository.BasketItemRepository;
import org.margomalanuha.spring.labs.repository.ProductRepository;
import org.margomalanuha.spring.labs.repository.PurchaseRepository;
import org.margomalanuha.spring.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private BasketItemRepository basketItemRepository;
    private ProductRepository productRepository;
    private PurchaseRepository purchaseRepository;

    @Autowired
    public void setBasketItemRepository(BasketItemRepository basketItemRepository) { this.basketItemRepository = basketItemRepository; }
    @Autowired
    public void setProductRepository(ProductRepository productRepository) { this.productRepository = productRepository; }
    @Autowired
    public void setPurchaseRepository(PurchaseRepository purchaseRepository) { this.purchaseRepository = purchaseRepository; }

    @Override
    public void addToBasket(Product product, User user) {
        basketItemRepository.create(new BasketItem(product.getId(), user.getId()));
    }

    @Override
    public void deleteFromBasket(Product product, User user) throws NoSuchElementException {
        BasketItem basketItem = basketItemRepository.getAll().stream()
                .filter(e -> (e.getProductId() == product.getId()) && (e.getUserId() == user.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new);
        basketItemRepository.delete(basketItem.getId());
    }

    @Override
    public List<Product> getBasket(User user) {
        return basketItemRepository.getAll().stream()
                .filter(e -> e.getUserId() == user.getId())
                .map(e -> productRepository.getById(e.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void clearBasket(User user) {
        basketItemRepository.getAll().stream()
                .filter(e -> e.getUserId() == user.getId())
                .forEach(e -> basketItemRepository.delete(e.getId()));
    }

    @Override
    public void addPurchaseToHistory(User user) {
        List<Product> list = getBasket(user);
        StringBuilder cheque = new StringBuilder();
        list.forEach(e -> cheque.append(e.toString()).append(" \n"));
        cheque.append(user.hashCode());
        double price = list.stream().mapToDouble(Product::getPrice).sum();
        purchaseRepository.create(new Purchase(user.getId(), price, cheque.toString()));
    }

    @Override
    public List<Purchase> getPurchaseHistory(User user) {
        return purchaseRepository.getAll().stream()
                .filter(e -> user.getId() == e.getUserId())
                .collect(Collectors.toList());
    }

    @Override
    public double returnPriceByCheque(String cheque, Product product) {
        Purchase purchase = purchaseRepository.getAll().stream()
                .filter(e -> e.getCheque().equals(cheque))
                .findAny().orElse(null);
        if (purchase == null || !purchase.getCheque().contains(product.getTitle())) return 0;
        double lastPrice = purchase.getPrice();
        purchase.setPrice(lastPrice - product.getPrice());
        return purchase.getPrice();
    }
}
