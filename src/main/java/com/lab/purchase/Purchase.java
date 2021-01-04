package com.lab.purchase;

import com.lab.products.Purchaseable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Purchase {

    private final long purchaseId;
    private List<Purchaseable> products = new ArrayList<>();

    public Purchase() {
        purchaseId = hashCode();
    }

    public Purchase(long purchaseId)
    {
        this.purchaseId = purchaseId;
    }

    public void add(Purchaseable product) { products.add(product); }

    public void delete(Purchaseable product)
    {
        products.remove(product);
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public Purchaseable getElementById(int index) {
        if (index >= products.size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            return products.get(index);
        }
    }

    public int size() {
        return products.size();
    }

    public List<Purchaseable> getProducts() {
        return products;
    }

    public void setProducts(List<Purchaseable> products) {
        this.products.clear();
        this.products = products;
    }

    public double getFullPrice() {
        double sum = 0;
        for (Purchaseable product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    public void printCheque() {
        Logger logger = LoggerFactory.getLogger(Purchase.class.getName());
        logger.debug("----------CHEQUE----------");
        logger.debug("Thank you for visiting us!");
        logger.debug("Your products:");
        for (Purchaseable product : products) {
            logger.debug("{}",product);
        }
        logger.debug("Your sum: {}", getFullPrice());
        logger.debug("See you next time!");
        logger.debug("PurchaseId: {}", purchaseId);
        logger.debug("----------CHEQUE----------");
    }
}
