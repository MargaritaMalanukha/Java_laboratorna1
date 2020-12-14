package FunctionalProgramming;

import com.FunctionalProgramming.CollectionProcessor;
import com.Lab.products.*;
import com.Lab.workers.Cleaner;
import com.Lab.workers.Manager;
import com.Lab.workers.Seller;
import com.Lab.workers.Worker;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class CollectionProcessorTest {

    ArrayList<Purchaseable> purchase = new ArrayList<>();
    ArrayList<Worker> workers = new ArrayList<>();

    @Before
    public void initPurchase() {
        purchase.add(MilkProduct.CHEESE);
        purchase.add(MilkProduct.MILK);
        purchase.add(MilkProduct.BUTTER);
        purchase.add(Fruit.ORANGE);
        purchase.add(Meat.HORSEMEAT);
        purchase.add(Sweet.CAKE);

        workers.add(new Seller(500));
        workers.add(new Manager(700));
        workers.add(new Manager(300));
        workers.add(new Seller(400));
        workers.add(new Cleaner(100));
        workers.add(new Cleaner(300));
    }

    @Test
    public void FindFullPriceByProductType_WithNonEmptyListAndClassnameToFilter_ShouldReturnFullPriceOfFilteredItems() {
        Assert.assertEquals(115.5, CollectionProcessor.findFullPriceByProductType(purchase, "com.Lab.products.MilkProduct"), 0.001);
    }

    @Test
    public void FindFullPriceByProductType_WithEmptyListAndClassnameToFilter_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findFullPriceByProductType(new ArrayList<>(), "com.Lab.products.MilkProduct"), 0);
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

    @Test
    public void PartitionByClassname_WithNonEmptyListAndValidClassname_ShouldReturnPartitionedMap() {
        Assert.assertEquals(3, CollectionProcessor.partitionByClassname(purchase, "com.Lab.products.MilkProduct").get(true).size());
        Assert.assertEquals(MilkProduct.BUTTER, CollectionProcessor.partitionByClassname(purchase, "com.Lab.products.MilkProduct").get(true).get(2));
    }

    @Test
    public void PartitionByClassname_WithEmptyListAndValidClassname_ShouldReturnMapWithEmptyValues() {
        Assert.assertEquals(0, CollectionProcessor.partitionByClassname(new ArrayList<>(), "com.Lab.products.MilkProduct").get(true).size());
    }

    @Test
    public void PartitionByClassname_WithNonEmptyListAndInvalidClassname_ShouldReturnMapWithEmptyValuesOnKeyTrueAndNonEmptyOnFalse() {
        Assert.assertEquals(0, CollectionProcessor.partitionByClassname(purchase, "wrongPath").get(true).size());
        Assert.assertEquals(6, CollectionProcessor.partitionByClassname(purchase, "wrongPath").get(false).size());
    }

    @Test
    public void FindMaxBonus_WithNonEmptyListAndValidClassname_ShouldReturnMaxBonus() {
        Assert.assertEquals(700, CollectionProcessor.findMaxBonus(workers, "com.Lab.workers.Manager"), 0.001);
    }

    @Test
    public void FindMaxBonus_WithEmptyListAndValidClassname_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findMaxBonus(new ArrayList<>(), "com.Lab.workers.Manager"), 0.001);
    }

    @Test
    public void FindMaxBonus_WithNonEmptyListAndInvalidClassname_ShouldReturnZero() {
        Assert.assertEquals(0, CollectionProcessor.findMaxBonus(workers, "wrongPath"), 0.001);
    }

}
