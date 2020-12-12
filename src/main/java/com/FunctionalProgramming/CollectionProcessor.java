package com.FunctionalProgramming;

import com.Lab.products.*;
import com.Lab.workers.Worker;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionProcessor {

    public static double findFullPriceByProductType(ArrayList<Purchaseable> purchase, String classname){
        return purchase
                .stream()
                .filter(c -> c.getClass().getName().equals(classname))
                .mapToDouble(Purchaseable::getPrice)
                .sum();
    }

    public static Purchaseable findTheMostExpensiveProduct(ArrayList<Purchaseable> purchase) {
        return purchase
                .stream()
                .max(Comparator.comparing(Purchaseable::getPrice))
                .orElseThrow(NoSuchElementException::new);
    }

    public static double findAverageProductPrice(ArrayList<Purchaseable> purchase) {
        return purchase
                .stream()
                .mapToDouble(Purchaseable::getPrice)
                .average()
                .orElse(0);
    }

    public static Map<Boolean, List<Purchaseable>> partitionByClassname(ArrayList<Purchaseable> purchase, String classname) {
        return purchase
                .stream()
                .collect(Collectors.partitioningBy(c -> c.getClass().getName().equals(classname)));
    }

    public static double findMaxBonus(ArrayList<Worker> workers, String classname) {
        return workers
                .stream()
                .filter(c -> c.getClass().getName().equals(classname))
                .mapToDouble(Worker::getBonus)
                .max()
                .orElse(0);
    }
}
