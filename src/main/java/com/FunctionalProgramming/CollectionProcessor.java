package com.FunctionalProgramming;

import com.Lab.products.*;
import com.Lab.workers.Cleaner;
import com.Lab.workers.Manager;
import com.Lab.workers.Seller;
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
                        e.printStackTrace();
                        return false;
                    }
                }).collect(Collectors.toCollection(ArrayList::new));
    }

    public static Worker findMaxBonus(ArrayList<Worker> workers, String classname) {
        return workers
                .stream()
                .filter(c -> {
                    try {
                        return Class.forName(classname).isInstance(c);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        return false;
                    }
                }).max(Comparator.comparing(Worker::getBonus)).get();
    }

    public static void main(String [] args) { //todo
        ArrayList<Purchaseable> purchase = new ArrayList<>();
        purchase.add(Fish.PIKE);
        purchase.add(Meat.CHICKEN);
        purchase.add(Fruit.ORANGE);
        purchase.add(MilkProduct.CHEESE);
        purchase.add(Meat.CHICKEN);

        System.out.println(Meat.class);

        Map<Boolean, ArrayList<Purchaseable>> map = filter(purchase, "com.Lab.products.Meat");

        map.forEach((k, v) -> System.out.println(k + " " + v));

        Cleaner cleaner = new Cleaner("Ivan", "Ivanov", 100);
        Cleaner cleaner2 = new Cleaner("Boris", "Borisov", 110);
        Cleaner cleaner3 = new Cleaner("Alex", "Petrov", 70);
        Cleaner cleaner4 = new Cleaner("Petr", "Sidorov", 0);
        Manager manager = new Manager("Vladislav", "Petrenko", 120);
        Seller seller = new Seller();

        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(cleaner);
        workers.add(cleaner2);
        workers.add(cleaner3);
        workers.add(cleaner4);
        workers.add(manager);
        workers.add(seller);

        Worker bestCleaner = findMaxBonus(workers, "com.Lab.workers.Cleaner");

        System.out.println("The best cleaner is " + bestCleaner.getName() + " " + bestCleaner.getSurname());

    }

}
