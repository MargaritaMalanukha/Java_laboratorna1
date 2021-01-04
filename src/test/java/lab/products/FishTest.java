package lab.products;

import com.lab.products.Fish;
import com.lab.products.Purchaseable;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class FishTest {

    private final ArrayList<Purchaseable> listOfFish = new ArrayList<>();
    {
        listOfFish.addAll(Arrays.asList(Fish.values()));
    }

    @Test
    public void ToString_WithPikeAsOutputField_ShouldReturnNameAndPriceOfProductInString() {
        String result = listOfFish.get(3).toString();

        Assert.assertEquals("The title of product is: Pike, price: 97.3", result);
    }

}
