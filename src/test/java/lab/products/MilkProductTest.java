package lab.products;

import com.lab.products.MilkProduct;
import com.lab.products.Purchaseable;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class MilkProductTest {

    private final ArrayList<Purchaseable> listOfMilkProducts = new ArrayList<>();
    {
        listOfMilkProducts.addAll(Arrays.asList(MilkProduct.values()));
    }

    @Test
    public void ToString_WithButterAsOutputField_ShouldReturnNameAndPriceOfProductInString() {
        String result = listOfMilkProducts.get(4).toString();

        Assert.assertEquals("The title of product is: Butter, price: 40.5", result);
    }

}
