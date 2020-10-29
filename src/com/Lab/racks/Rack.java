package com.Lab.racks;

import com.Lab.exceptions.WrongProductTypeException;
import com.Lab.products.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Rack {

    private String productTitle;
    private HashSet<Purchaseable> products = new HashSet<>(); //неявно используется непереопределенный equals & hashcode.

    public Rack(String productTitle) {
        this.productTitle = productTitle;
        try {
            generateRack();
        } catch (WrongProductTypeException e) {
            System.out.println("WrongProductTypeException: rack cannot be generated. ");
        }
    }

    public void generateRack() {
        switch (productTitle) {
            case "Fish" -> products.addAll(Arrays.asList(Fish.values()));
            case "Fruit" -> products.addAll(Arrays.asList(Fruit.values()));
            case "Meat" -> products.addAll(Arrays.asList(Meat.values()));
            case "MilkProduct" -> products.addAll(Arrays.asList(MilkProduct.values()));
            case "Sweet" -> products.addAll(Arrays.asList(Sweet.values()));
            case "Vegetable" -> products.addAll(Arrays.asList(Vegetable.values()));
            default -> throw new WrongProductTypeException(productTitle);
        }
    }

    public void printRack() {
        System.out.println(productTitle + " products:");
        Iterator<Purchaseable> iterator = products.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + " ");
        }
    }

    public HashSet<Purchaseable> getProducts() {
        return products;
    }
}
