package com.Lab.racks;

import com.Lab.exceptions.WrongProductTypeException;
import com.Lab.products.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class Rack {

    private final String productTitle;
    private final HashSet<Purchaseable> products = new HashSet<>(); //неявно используется непереопределенный hashcode.

    public Rack(String productTitle) {
        this.productTitle = productTitle;
        try {
            generateRack();
        } catch (WrongProductTypeException e) {
            System.out.println("WrongProductTypeException: rack cannot be generated. ");
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
