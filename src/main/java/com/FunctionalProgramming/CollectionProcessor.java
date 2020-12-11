package com.FunctionalProgramming;

import com.Lab.products.*;
import com.Lab.workers.Worker;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionProcessor {

    public static double findMilkFullPrice(ArrayList<Purchaseable> purchase){ //todo

        return purchase
                .stream()
                .filter(c -> c instanceof MilkProduct)
                .mapToDouble(Purchaseable::getPrice).sum();
    }

    public static Optional<Purchaseable> findTheMostExpensiveProduct(ArrayList<Purchaseable> purchase) { //todo
        Purchaseable product = Collections.max(purchase, Purchaseable::compare);
        return Optional.ofNullable(product);
    }

    public static double averageProductPrice(ArrayList<Purchaseable> purchase) {
        return purchase
                .stream()
                .mapToDouble(Purchaseable::getPrice)
                .average()
                .orElse(0);
    }

 /*  public static Map<Boolean, ArrayList<Purchaseable>> filter(ArrayList<Purchaseable> purchase, String classname) {//todo
        return purchase
                .stream()
                .collect(Collectors.partitioningBy(c -> {
                    if (c instanceof )
                }));

    }*/

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

  /*  public static double findMaxBonus(ArrayList<Worker> workers, String classname) { //todo
        return workers
                .stream()
                .filter(CollectionProcessor::exceptionHandler)
                .mapToDouble(Worker::getBonus)
                .max()
                .orElse(0);
    }*/

 /*   private static boolean exceptionHandler(Worker worker) {
        try {
            return Class.forName(classname).isInstance(worker);
        } catch (MyException e) {
            return false;
        }
    }*/
}
