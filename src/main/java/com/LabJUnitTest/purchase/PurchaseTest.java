package com.LabJUnitTest.purchase;

import com.Lab.products.*;
import com.Lab.purchase.Purchase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PurchaseTest {

    private final Purchase purchase = new Purchase(12345);

    @Before
    public void init() {
        purchase.add(MilkProduct.MILK);
        purchase.add(Meat.HORSEMEAT);
        purchase.add(Sweet.CAKE);
    }

    @Test
    public void Add_WithSomeProducts_ShouldAddElementInList() {
        Assert.assertEquals(3, purchase.size());
    }

    @Test
    public void Delete_WithSomeProducts_ShouldDeleteElementList() {
        purchase.delete(Meat.HORSEMEAT);
        Assert.assertEquals(2, purchase.size());
    }

    @Test
    public void GetElementById_WithValidId_ShouldReturnProductId() {
        Assert.assertEquals(Meat.HORSEMEAT, purchase.getElementById(1));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void GetElementById_WithIdBiggerThanListSize_ShouldReturnArrayIndexOutOfBoundsException() {
        Purchaseable product = purchase.getElementById(3);
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void GetElementById_WithIdSmallerThanListSize_ShouldReturnArrayIndexOutOfBoundsException() {
        Purchaseable product = purchase.getElementById(-1);
    }

    @Test
    public void GetFullPrice_WithNonEmptyPurchaseListGiven_ShouldReturnFullProductsPrice() {
        Assert.assertEquals(485.99, purchase.getFullPrice(), 0);
    }

    @Test
    public void GetFullPrice_WithEmptyPurchaseListGiven_ShouldReturnZero() {
        Purchase emptyPurchase = new Purchase(1);
        Assert.assertEquals(0, emptyPurchase.getFullPrice(), 0);
    }
}
