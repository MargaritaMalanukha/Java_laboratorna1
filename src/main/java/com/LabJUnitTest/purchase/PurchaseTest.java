package com.LabJUnitTest.purchase;

import com.Lab.products.*;
import com.Lab.purchase.Purchase;
import org.junit.Assert;
import org.junit.Test;

public class PurchaseTest {

    private final Purchase purchase = new Purchase(12345);

    @Test
    public void testAddProducts() {
        purchase.add(MilkProduct.BUTTER);
        purchase.add(Meat.CHICKEN);
        purchase.add(Vegetable.TOMATO);
        purchase.add(Vegetable.CARROT);
        purchase.add(Sweet.CAKE);
        purchase.add(Fruit.MELON);
        purchase.add(Fish.ANCHOVY);

        Assert.assertEquals(7, purchase.size());
    }

    @Test
    public void testDeleteProducts() {
        purchase.add(MilkProduct.MILK);
        purchase.add(Meat.HORSEMEAT);
        purchase.add(Sweet.CAKE);

        purchase.delete(Meat.HORSEMEAT);

        Assert.assertEquals(2, purchase.size());
    }

    @Test
    public void testGetElementById() {
        purchase.add(MilkProduct.MILK);
        purchase.add(MilkProduct.KEFIR);
        purchase.add(Meat.PORK);
        purchase.add(Fruit.PINEAPPLE);

        Assert.assertEquals(MilkProduct.KEFIR, purchase.getElementById(1));
    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void testGetBiggerThanSizeElementById() {
        purchase.add(Meat.BEEF);
        purchase.add(Fish.PIKE);
        purchase.add(Vegetable.CARROT);
        purchase.add(Meat.HORSEMEAT);
        purchase.add(MilkProduct.YOGURT);

        Purchaseable product = purchase.getElementById(5);

    }

    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void testGetSmallerThanSizeElementById() {
        purchase.add(MilkProduct.YOGURT);
        purchase.add(Sweet.CAKE);

        Purchaseable product = purchase.getElementById(-1);
    }

    @Test
    public void testGetFullPrice() {
        purchase.add(Meat.TURKEY_MEAT);
        purchase.add(Vegetable.CARROT);
        purchase.add(Fish.ANCHOVY);
        purchase.add(Fish.PERCH);
        purchase.add(Fruit.PINEAPPLE);
        purchase.add(Sweet.CHOCOLATE);
        purchase.add(MilkProduct.CHEESE);

        Assert.assertEquals(413.72, purchase.getFullPrice(), 0);
    }
}
