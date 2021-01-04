package com.functionalprogramming;

import com.lab.products.*;
import com.lab.purchase.Purchase;
import com.lab.workers.Worker;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CollectionProcessor {

    private CollectionProcessor() { }

    public static double findFullPriceByProductType(List<Purchaseable> purchase, String classname){
        return purchase
                .stream()
                .filter(c -> c.getClass().getName().equals(classname))
                .mapToDouble(Purchaseable::getPrice)
                .sum();
    }

    public static Purchaseable findTheMostExpensiveProduct(List<Purchaseable> purchase) {
        return purchase
                .stream()
                .max(Comparator.comparing(Purchaseable::getPrice))
                .orElseThrow(NoSuchElementException::new);
    }

    public static double findAverageProductPrice(List<Purchaseable> purchase) {
        return purchase
                .stream()
                .mapToDouble(Purchaseable::getPrice)
                .average()
                .orElse(0);
    }

    public static Map<Boolean, List<Worker>> partitionByCondition(List<Worker> workers, Predicate<Worker> condition) {
        return workers
                .stream()
                .collect(Collectors.partitioningBy(condition));
    }

    public static List<String> findMostFrequentProduct(List<Purchase> purchases) {
        List <String> result = new ArrayList<>();
        purchases.stream()
                .flatMap(x -> x.getProducts().stream())
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(Purchaseable::getTitle))
                .forEach((key, value) -> value.stream()
                        .collect(Collectors.groupingBy(Purchaseable::getTitle, Collectors.counting()))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .ifPresent(x-> result.add(x.getKey()))
                );
        return result;
    }
}
