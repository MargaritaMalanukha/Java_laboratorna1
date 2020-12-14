package Lab.products;

import com.Lab.products.MilkProduct;
import com.Lab.products.Purchaseable;
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

        Assert.assertEquals("Название продукта: Масло, цена: 40.5", result);
    }

}
