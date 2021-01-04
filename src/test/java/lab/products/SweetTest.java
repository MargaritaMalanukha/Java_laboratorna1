package lab.products;

import com.lab.products.Sweet;
import com.lab.products.Purchaseable;
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
    public void ToString_WithCakeAsOutputField_ShouldReturnNameAndPriceOfProductInString() {
        String result = listOfSweet.get(2).toString();

        Assert.assertEquals("The title of product is: Cake, price: 120.99", result);
    }

}
