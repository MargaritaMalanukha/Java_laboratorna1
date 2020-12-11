package com.LabJUnitTest.products;

import com.Lab.products.Meat;
import com.Lab.products.Purchaseable;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MeatTest {

    private final ArrayList<Purchaseable> listOfMeat = new ArrayList<>();
    {
        listOfMeat.addAll(Arrays.asList(Meat.values()));
    }

    @Test
    public void ToString_WithChickenAsOutputField_ShouldReturnNameAndPriceOfProductInString() {
        String result = listOfMeat.get(0).toString();

        Assert.assertEquals("Название продукта: Курица, цена: 90.0", result);
    }

}
