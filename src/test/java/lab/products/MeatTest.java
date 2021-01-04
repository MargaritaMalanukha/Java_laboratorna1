package lab.products;

import com.lab.products.Meat;
import com.lab.products.Purchaseable;
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

        Assert.assertEquals("The title of product is: Chicken, price: 90.0", result);
    }

}
