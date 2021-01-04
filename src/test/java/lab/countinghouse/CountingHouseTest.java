package lab.countinghouse;

import com.lab.countinghouse.CountingHouse;
import com.lab.workers.Cleaner;
import com.lab.workers.Manager;
import com.lab.workers.Seller;
import org.junit.Assert;
import org.junit.Test;

public class CountingHouseTest {

    private final CountingHouse countingHouse = new CountingHouse();
    {
        countingHouse.increaseMoney(1500);
    }
    private final Cleaner cleaner = new Cleaner();
    private final Seller seller = new Seller();
    private final Manager manager = new Manager();

    @Test
    public void PaySalary_WithWorkedHoursAreEqualToNormal_ShouldReturnDecreasedMoney() {
        cleaner.setWorkingHours(8);
        seller.setWorkingHours(9);
        manager.setWorkingHours(7);

        countingHouse.paySalary(cleaner, seller, manager);

        Assert.assertEquals(100, countingHouse.getMoney(), 0);
    }

    @Test
    public void PaySalary_WithWorkedHoursAreNotEqualToNormal_ShouldReturnNotChangedMoney() {
        cleaner.setWorkingHours(2);
        seller.setWorkingHours(1);
        manager.setWorkingHours(5);

        countingHouse.paySalary(cleaner, seller, manager);

        Assert.assertEquals(1500, countingHouse.getMoney(), 0);
    }

}
