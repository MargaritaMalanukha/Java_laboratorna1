package com.FunctionalProgramming;

import com.Lab.products.*;
import com.Lab.workers.Worker;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionProcessor {

    public static double findMilkFullPrice(ArrayList<Purchaseable> purchase){

        return purchase
                .stream()
                .filter(c -> c instanceof MilkProduct)
                .mapToDouble(Purchaseable::getPrice).sum();
    }

    public static Optional<Purchaseable> findTheMostExpensiveProduct(ArrayList<Purchaseable> purchase) {
        Purchaseable product = purchase.size() > 0 ? Collections
                .max(purchase, Purchaseable::compare) : null;
        return Optional.ofNullable(product);
    }

    public static double averageProductPrice(ArrayList<Purchaseable> purchase) {
        return purchase
                .stream()
                .mapToDouble(Purchaseable::getPrice)
                .average()
                .orElse(0);
    }

   public static Map<Boolean, ArrayList<Purchaseable>> filter(ArrayList<Purchaseable> purchase, String classname) {
        Map<Boolean, ArrayList<Purchaseable>> map = new HashMap<>();
       map.put(true, generateFilteredList(purchase, true, classname));
       map.put(false, generateFilteredList(purchase, false, classname));
       return map;

    }

    public static ArrayList<Purchaseable> generateFilteredList(ArrayList<Purchaseable> purchase, boolean putFilteredProducts, String classname) {
        return purchase
                .stream()
                .filter(c -> {
                    try {
                        if (putFilteredProducts) return Class.forName(classname).isInstance(c);
                        return !Class.forName(classname).isInstance(c);
                    } catch (ClassNotFoundException e) {
                        return false;
                    }
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    public static double findMaxBonus(ArrayList<Worker> workers, String classname) {
        return workers
                .stream()
                .filter(c -> {
                    try {
                        return Class.forName(classname).isInstance(c);
                    } catch (ClassNotFoundException e) {
                        return false;
                    }
                })
                .mapToDouble(Worker::getBonus)
                .max()
                .orElse(0);
    }

}
