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
    public void addToBasket(int productId, int userId) {
        Product product = new Product();
        product.setId(productId);
        User user = new User();
        user.setId(userId);
        basketItemRepository.save(new BasketItem(product, user));
    }

    @Override
    public void deleteFromBasket(int productId, int userId) throws NoSuchElementException {
        BasketItem basketItem = basketItemRepository.findAll().stream()
                .filter(e -> (e.getProduct().getId() == productId) && (e.getUser().getId() == userId))
                .findFirst().orElseThrow(NoSuchElementException::new);
        basketItemRepository.delete(basketItem);
    }

    @Override
    public List<Product> getBasket(int userId) {
        return basketItemRepository.findAll().stream()
                .filter(e -> e.getUser().getId() == userId)
                .map(e -> productRepository.findById(e.getProduct().getId())
                        .orElse(new Product("unknown product", 0, new Catalog())))
                .collect(Collectors.toList());
    }

    @Override
    public void clearBasket(int userId) {
        basketItemRepository.findAll().stream()
                .filter(e -> e.getUser().getId() == userId)
                .forEach(e -> basketItemRepository.delete(e));
    }

    @Override
    public Purchase addPurchaseToHistory(int userId) {
        List<Product> list = getBasket(userId);
        StringBuilder cheque = new StringBuilder();
        list.forEach(e -> cheque.append(e.toString()).append(" \n"));
        System.out.println(cheque);
        User user = new User();
        user.setId(userId);
        cheque.append(user.hashCode());
        double price = list.stream().mapToDouble(Product::getPrice).sum();
        return purchaseRepository.save(new Purchase(user, price, cheque.toString()));
    }

    @Override
    public List<Purchase> getPurchaseHistory(int userId) {
        return purchaseRepository.findAll().stream()
                .filter(e -> userId == e.getUser().getId())
                .collect(Collectors.toList());
    }

    @Override
    public List<Purchase> getAllNonFinishedPurchases() {
        return purchaseRepository.findAll()
                .stream()
                .filter(e -> e.getStatus().equalsIgnoreCase("not started"))
                .collect(Collectors.toList());
    }


}
