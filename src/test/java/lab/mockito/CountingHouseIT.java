package lab.mockito;

import com.lab.countinghouse.CountingHouse;
import com.lab.workers.Cleaner;
import com.lab.workers.Manager;
import com.lab.workers.Seller;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class CountingHouseIT {

    private Seller seller = Mockito.mock(Seller.class);
    private Cleaner cleaner = Mockito.mock(Cleaner.class);
    private Manager manager = Mockito.mock(Manager.class);
    CountingHouse countingHouse = new CountingHouse();

    @Before
    public void init() {
        countingHouse.increaseMoney(2000);

        Mockito.when(seller.getSalary()).thenReturn(500);
        Mockito.when(cleaner.getSalary()).thenReturn(300);
        Mockito.when(manager.getSalary()).thenReturn(600);
    }

    @Test
    public void WhenWorkersAreIdeal_ThenDecreaseProductMarketMoney() {
        Mockito.when(seller.getWorkingHours()).thenReturn(9);
        Mockito.when(cleaner.getWorkingHours()).thenReturn(8);
        Mockito.when(manager.getWorkingHours()).thenReturn(7);

        countingHouse.paySalary(cleaner, seller, manager);

        Assert.assertEquals(600.0, countingHouse.getMoney(), 0.001);
    }

    @Test
    public void WhenWorkersAreNotIdeal_ThenDecreaseProductMarketMoney() {
        Mockito.when(seller.getWorkingHours()).thenReturn(1);
        Mockito.when(cleaner.getWorkingHours()).thenReturn(2);
        Mockito.when(manager.getWorkingHours()).thenReturn(3);

        countingHouse.paySalary(cleaner, seller, manager);

        Assert.assertEquals(2000.0, countingHouse.getMoney(), 0.001);
    }

}
