package com.FunctionalProgramming;

import com.Lab.products.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CollectionProcessor {

    public static double findMilkFullPrice(ArrayList<Purchaseable> purchase){

        return purchase
                .stream()
                .filter(c -> c instanceof MilkProduct)
                .mapToDouble(Purchaseable::getPrice).sum();
    }

    public static Purchaseable findTheMostExpensiveProduct(ArrayList<Purchaseable> purchase) {
        return Collections
                .max(purchase, Purchaseable::compare);
    }

    public static double averageProductPrice(ArrayList<Purchaseable> purchase) {
        return purchase
                .stream()
                .mapToDouble(Purchaseable::getPrice)
                .average()
                .orElse(0);
    }

   /* public static Map<Boolean, ArrayList<Purchaseable>> filter(ArrayList<Purchaseable> purchase, Class<T> cls) {
        Map<Boolean, ArrayList<Purchaseable>> map = new HashMap<>();
        map.put(true, purchase
                .stream()
                .filter(c -> c instanceof cls));
    }*/

  //  public static

    public static void main(String [] args) {
        ArrayList<Purchaseable> purchase = new ArrayList<>();
        purchase.add(Fish.PIKE);
        purchase.add(Meat.CHICKEN);
        purchase.add(Fruit.ORANGE);
        purchase.add(MilkProduct.CHEESE);

        System.out.println(purchase.get(3).getClass());

        System.out.println(findMilkFullPrice(purchase));
    }

}
