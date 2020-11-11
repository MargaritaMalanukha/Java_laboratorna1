package com.LabJUnitTest.products;

import com.Lab.products.Sweet;
import com.Lab.products.Purchaseable;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class SweetTest {

    private final ArrayList<Purchaseable> listOfSweet = new ArrayList<>();
    {
        listOfSweet.addAll(Arrays.asList(Sweet.values()));
    }

    @Test
    public void testArrayListElementToString() {
        String result = listOfSweet.get(2).toString();

        Assert.assertEquals("Название продукта: Торт, цена: 120.99", result);
    }

}
