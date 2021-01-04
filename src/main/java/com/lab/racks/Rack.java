package com.lab.racks;

import com.lab.exceptions.WrongProductTypeException;
import com.lab.products.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Rack {

    private final String productTitle;
    private final HashSet<Purchaseable> products = new HashSet<>(); //неявно используется непереопределенный hashcode.

    public Rack(String productTitle) {
        Logger logger = LoggerFactory.getLogger(Rack.class);
        this.productTitle = productTitle;
        try {
            generateRack();
        } catch (WrongProductTypeException e) {
            logger.debug("WrongProductTypeException: rack cannot be generated. ");
        }
    }

    public void generateRack() {
        if (productTitle.equals("Fish"))  products.addAll(Arrays.asList(Fish.values()));
        else if(productTitle.equals("Fruit")) products.addAll(Arrays.asList(Fruit.values()));
        else if(productTitle.equals("Meat")) products.addAll(Arrays.asList(Meat.values()));
        else if(productTitle.equals("MilkProduct")) products.addAll(Arrays.asList(MilkProduct.values()));
        else if(productTitle.equals("Sweet")) products.addAll(Arrays.asList(Sweet.values()));
        else if(productTitle.equals("Vegetable")) products.addAll(Arrays.asList(Vegetable.values()));
        else {
            throw new WrongProductTypeException(productTitle);
        }
    }

    public void printRack() {
        Logger logger = LoggerFactory.getLogger(Rack.class);
        logger.debug("{} products:", productTitle);
        for (Purchaseable product : products) {
            logger.debug("{} ", product.getTitle());
        }
    }

    public Set<Purchaseable> getProducts() {
        return products;
    }
}
