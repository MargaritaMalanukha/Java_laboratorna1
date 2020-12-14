package Lab.products;

import com.Lab.products.Fish;
import com.Lab.products.Purchaseable;
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

        Assert.assertEquals("Название продукта: Щука, цена: 97.3", result);
    }

}
