package com.LabJUnitTest.products;

import com.Lab.products.Fruit;
import com.Lab.products.Purchaseable;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class FruitTest {

    private final ArrayList<Purchaseable> listOfFruit = new ArrayList<>();
    {
        listOfFruit.addAll(Arrays.asList(Fruit.values()));
    }

    @Test
    public void ToString_WithOrangeAsOutputField_ShouldReturnNameAndPriceOfProductInString() {
        String result = listOfFruit.get(2).toString();

        Assert.assertEquals("Название продукта: Апельсин, цена: 42.5", result);
    }

}
