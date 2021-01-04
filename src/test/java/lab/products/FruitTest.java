package lab.products;

import com.lab.products.Fruit;
import com.lab.products.Purchaseable;
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

        Assert.assertEquals("The title of product is: Orange, price: 42.5", result);
    }

}
