package com.FunctionalProgramming;

import com.Lab.products.*;
import com.Lab.workers.Cleaner;
import com.Lab.workers.Manager;
import com.Lab.workers.Seller;
import com.Lab.workers.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectionProcessorTest {

    ArrayList<Purchaseable> arrayList = new ArrayList<>();
    ArrayList<Worker> workers = new ArrayList<>();

    @Test
    public void findMilkFullPrice_WithEmptyList_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findMilkFullPrice(arrayList), 0.001);
    }

    @Test
    public void findMilkFullPrice_WithNoMilkInList_ShouldReturnZero() {
        arrayList.add(Fish.PIKE);
        arrayList.add(Meat.HORSEMEAT);
        arrayList.add(Vegetable.CARROT);
        arrayList.add(Vegetable.ONION);
        arrayList.add(Fish.ANCHOVY);

        Assert.assertEquals(0, CollectionProcessor.findMilkFullPrice(arrayList), 0.001);

    }

    @Test
    public void findMilkFullPrice_WithMilkInList_ShouldReturnMilkFullPrice() {
        arrayList.add(MilkProduct.CHEESE);
        arrayList.add(MilkProduct.BUTTER);
        arrayList.add(MilkProduct.CHEESE);
        arrayList.add(Vegetable.ONION);

        Assert.assertEquals(140.5, CollectionProcessor.findMilkFullPrice(arrayList), 0.001);
    }

    @Test
    public void findTheMostExpensiveProduct_WithEmptyList_ShouldReturnEmptyList() {
        Optional<Purchaseable> optional = CollectionProcessor.findTheMostExpensiveProduct(arrayList);
        Assert.assertFalse(optional.isPresent());
    }

    @Test
    public void findTheMostExpensiveProduct_WithProductList_ShouldReturnProduct() {
        arrayList.add(Vegetable.ONION);
        arrayList.add(Meat.PORK);
        arrayList.add(Fruit.ORANGE);

        Assert.assertEquals(305.5, CollectionProcessor.findTheMostExpensiveProduct(arrayList).get().getPrice(), 0.001);
    }

    @Test
    public void averageProductPrice_WithEmptyList_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.averageProductPrice(arrayList), 0.001);
    }

    @Test
    public void averageProductPrice_WithProductList_ShouldReturnAverage() {
        arrayList.add(Meat.HORSEMEAT);
        arrayList.add(MilkProduct.KEFIR);
        arrayList.add(Vegetable.ONION);

        Assert.assertEquals(122.76667, CollectionProcessor.averageProductPrice(arrayList), 0.001);
    }

 /*   @Test
    public void filter_WrongClassnameEntered_ShouldReturnEmptyList() {
        arrayList.add(Meat.HORSEMEAT);
        arrayList.add(Meat.PORK);

        ArrayList<Purchaseable> filteredListFromMap = CollectionProcessor.filter(arrayList, "com.Lab.products.wrongclassname").get(true);
        ArrayList<Purchaseable> nonfilteredListFromMap = CollectionProcessor.filter(arrayList, "com.Lab.products.wrongclassname").get(false);

        Assert.assertTrue(filteredListFromMap.isEmpty());
        Assert.assertTrue(nonfilteredListFromMap.isEmpty());
    }*/

 /*   @Test
    public void filter_Meat_ShouldReturnFilteredProductsAndNonFilteredProductsSeparately() {
        arrayList.add(Meat.PORK);
        arrayList.add(Meat.TURKEY_MEAT);
        arrayList.add(Meat.CHICKEN);
        arrayList.add(Meat.HORSEMEAT);
        arrayList.add(Vegetable.PEPPER);
        arrayList.add(Fruit.ORANGE);
        arrayList.add(MilkProduct.KEFIR);

        String classname = "com.Lab.products.Meat";

        ArrayList<Purchaseable> filteredListFromMap = CollectionProcessor.filter(arrayList, classname).get(true);
        ArrayList<Purchaseable> nonfilteredListFromMap = CollectionProcessor.filter(arrayList, classname).get(false);

        Assert.assertEquals(Meat.PORK, filteredListFromMap.get(0));
        Assert.assertEquals(4, filteredListFromMap.size());
        Assert.assertEquals(MilkProduct.KEFIR, nonfilteredListFromMap.get(2));
        Assert.assertEquals(3, nonfilteredListFromMap.size());
    } */

  /*  @Test
    public void findMaxBonus_WorkerListIsEmpty_ReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findMaxBonus(workers, "com.Lab.products.Cleaner"), 0.001);
    }

    @Test
    public void findMaxBonus_WrongClassName_ReturnZero() {
        initWorker();
        Assert.assertEquals(0, CollectionProcessor.findMaxBonus(workers, "com.Lab.products.wrongclassname"), 0.001);
    }

    @Test
    public void findMaxBonus_WithWorkerList_ReturnMaxBonus() {
        initWorker();
        Assert.assertEquals(110, CollectionProcessor.findMaxBonus(workers, "com.Lab.workers.Cleaner"), 0.001);
    }*/

    private void initWorker() {
        workers.add(new Cleaner("Ivan", "Ivanov", 100));
        workers.add(new Cleaner("Boris", "Borisov", 110));
        workers.add(new Cleaner("Alex", "Petrov", 70));
        workers.add(new Cleaner("Petr", "Sidorov", 0));
        workers.add(new Manager("Vladislav", "Petrenko", 120));
        workers.add(new Seller("Vladislav", "Petrenko", 30));
    }


}
