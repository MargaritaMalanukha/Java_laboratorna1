package lab.workers;

import com.lab.products.Fruit;
import com.lab.products.Meat;
import com.lab.products.MilkProduct;
import com.lab.purchase.Purchase;
import com.lab.workers.Seller;
import org.junit.Assert;
import org.junit.Test;

public class SellerTest {

    private final Seller seller = new Seller();
    {
        seller.setWorkingHours(9);
    }

    @Test
    public void Pay_WithMoneyGetFromCustomer_ShouldSaveMoneyAndSendASuccessMessage() {
        Purchase purchase = new Purchase();
        purchase.add(Meat.TURKEY_MEAT);
        purchase.add(MilkProduct.CHEESE);
        purchase.add(Fruit.PINEAPPLE);

        seller.pay(purchase);

        Assert.assertEquals(195.62, seller.getMoney(), 0);
    }

}