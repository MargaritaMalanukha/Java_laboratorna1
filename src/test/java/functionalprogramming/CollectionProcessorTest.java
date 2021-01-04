package functionalprogramming;

import com.functionalprogramming.CollectionProcessor;
import com.lab.products.*;
import com.lab.purchase.Purchase;
import com.lab.workers.Cleaner;
import com.lab.workers.Manager;
import com.lab.workers.Seller;
import com.lab.workers.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CollectionProcessorTest {

    ArrayList<Purchaseable> purchase = new ArrayList<>();
    ArrayList<Purchase> purchases = new ArrayList<>();
    ArrayList<Worker> workers = new ArrayList<>();

    @Before
    public void initPurchase() {
        purchase.add(MilkProduct.CHEESE);
        purchase.add(MilkProduct.MILK);
        purchase.add(MilkProduct.BUTTER);
        purchase.add(Fruit.ORANGE);
        purchase.add(Meat.HORSEMEAT);
        purchase.add(Sweet.CAKE);

        workers.add(new Seller("Alex", "Petrov", 500));
        workers.add(new Manager("Bohdan", "Sidorov", 700));
        workers.add(new Manager("Bohdan", "Alchevsky",300));
        workers.add(new Manager("Alex", "Simonov", 700));
        workers.add(new Manager("Semion", "Semyonov", 300));
        workers.add(new Seller("Petr", "Malanukha",400));
        workers.add(new Cleaner("Irina", "Dovgan",100));
        workers.add(new Cleaner("Stas", "Sergeev",300));

        Purchase purchase1 = new Purchase();
        purchase1.setProducts(purchase);
        Purchase purchase2 = new Purchase();
        purchase2.add(MilkProduct.MILK);
        purchase2.add(Fish.SALMON);
        purchase2.add(Fruit.MELON);
        purchase2.add(Fruit.ORANGE);
        purchases.add(purchase1);
        purchases.add(purchase2);
    }

    @Test
    public void FindFullPriceByProductType_WithNonEmptyListAndClassnameToFilter_ShouldReturnFullPriceOfFilteredItems() {
        Assert.assertEquals(115.5, CollectionProcessor.findFullPriceByProductType(purchase, "com.lab.products.MilkProduct"), 0.001);
    }

    @Test
    public void FindFullPriceByProductType_WithEmptyListAndClassnameToFilter_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findFullPriceByProductType(new ArrayList<>(), "com.lab.products.MilkProduct"), 0);
    }

    @Test
    public void FindFullPriceByProductType_WithNonEmptyListAndInvalidClassnameToFilter_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findFullPriceByProductType(purchase, "wrongClassname"), 0);
    }

    @Test
    public void FindTheMostExpensiveProduct_WithNonEmptyList_ShouldReturnProductWithMaxPrice() {
        Assert.assertEquals(340, CollectionProcessor.findTheMostExpensiveProduct(purchase).getPrice(), 0);
    }

    @Test (expected = NoSuchElementException.class)
    public void FindTheMostExpensiveProduct_WithEmptyList_ShouldReturnNoSuchElementException() {
        CollectionProcessor.findTheMostExpensiveProduct(new ArrayList<>());
    }

    @Test
    public void AverageProductPrice_WithNonEmptyList_ShouldReturnAveragePriceForPurchase() {
        Assert.assertEquals(103.165, CollectionProcessor.findAverageProductPrice(purchase), 0.001);
    }

    @Test
    public void AverageProductPrice_WithEmptyList_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findAverageProductPrice(new ArrayList<>()), 0.001);
    }

 /*   @Test
    public void PartitionByCondition_WithNonEmptyListAndValidCondition_ShouldReturnPartitionedMap() {
        Assert.assertEquals(2, CollectionProcessor.partitionByCondition(workers, worker -> worker instanceof Manager).get(true).size());
        Assert.assertEquals(100, CollectionProcessor.partitionByCondition(workers, worker -> worker instanceof Manager).get(false).get(2).getBonus(), 0.001);
    }*/

    @Test
    public void PartitionByCondition_WithEmptyListAndValidCondition_ShouldReturnMapWithEmptyValues() {
        Assert.assertEquals(0, CollectionProcessor.partitionByCondition(new ArrayList<>(), worker -> worker instanceof Seller).get(true).size());
    }

   /* @Test
    public void FindMostFrequentProduct_WithNonEmptyList_ShouldReturnListWithTheMostFrequentProducts() {
        Assert.assertEquals(2, CollectionProcessor.findMostFrequentProduct(purchases).size());
    }*/

 /*   @Test
    public void FindMaxBonus_WithEmptyListAndValidClassname_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findMaxBonus(new ArrayList<>(), "com.Lab.workers.Manager"), 0.001);
    }

    @Test
    public void FindMaxBonus_WithNonEmptyListAndInvalidClassname_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findMaxBonus(workers, "wrongPath"), 0.001);
    }*/

}
