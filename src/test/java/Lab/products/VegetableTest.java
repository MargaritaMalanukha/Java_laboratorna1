package Lab.products;

import com.Lab.products.Purchaseable;
import com.Lab.products.Vegetable;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class VegetableTest {

    private final ArrayList<Purchaseable> listOfVegetable = new ArrayList<>();
    {
        listOfVegetable.addAll(Arrays.asList(Vegetable.values()));
    }

    @Test
    public void ToString_WithOnionAsOutputField_ShouldReturnNameAndPriceOfProductInString() {
        String result = listOfVegetable.get(4).toString();

        Assert.assertEquals("Название продукта: Лук, цена: 5.0", result);
    }

}
