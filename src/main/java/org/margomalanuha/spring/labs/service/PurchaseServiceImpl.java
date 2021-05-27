package org.margomalanuha.spring.labs.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.*;
import org.margomalanuha.spring.labs.repository.BasketItemRepository;
import org.margomalanuha.spring.labs.repository.ProductRepository;
import org.margomalanuha.spring.labs.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@NoArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Getter
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
        basketItemRepository.save(new BasketItem(product, user));
    }

    @Override
    public void deleteFromBasket(Product product, User user) throws NoSuchElementException {
        BasketItem basketItem = basketItemRepository.findAll().stream()
                .filter(e -> (e.getProduct().getId() == product.getId()) && (e.getUser().getId() == user.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new);
        basketItemRepository.delete(basketItem);
    }

    @Override
    public List<Product> getBasket(User user) {
        return basketItemRepository.findAll().stream()
                .filter(e -> e.getUser().getId() == user.getId())
                .map(e -> productRepository.findById(e.getProduct().getId())
                        .orElse(new Product("unknown product", 0, new Catalog())))
                .collect(Collectors.toList());
    }

    @Override
    public void clearBasket(User user) {
        basketItemRepository.findAll().stream()
                .filter(e -> e.getUser().getId() == user.getId())
                .forEach(e -> basketItemRepository.delete(e));
    }

    @Override
    public void addPurchaseToHistory(User user) {
        List<Product> list = getBasket(user);
        StringBuilder cheque = new StringBuilder();
        list.forEach(e -> cheque.append(e.toString()).append(" \n"));
        System.out.println(cheque);
        cheque.append(user.hashCode());
        double price = list.stream().mapToDouble(Product::getPrice).sum();
        purchaseRepository.save(new Purchase(user, price, cheque.toString()));
    }

    @Override
    public List<Purchase> getPurchaseHistory(User user) {
        return purchaseRepository.findAll().stream()
                .filter(e -> user.getId() == e.getUser().getId())
                .collect(Collectors.toList());
    }

    @Override
    public double returnPriceByCheque(String cheque, Product product) {
        Purchase purchase = purchaseRepository.findAll().stream()
                .filter(e -> e.getCheque().equals(cheque))
                .findAny().orElse(null);
        if (purchase == null || !purchase.getCheque().contains(product.getTitle())) return 0;
        double lastPrice = purchase.getPrice();
        purchase.setPrice(lastPrice - product.getPrice());
        return purchase.getPrice();
    }
}
