package com.LabJUnitTest.continghouse;

import com.Lab.countinghouse.CountingHouse;
import com.Lab.workers.Cleaner;
import com.Lab.workers.Manager;
import com.Lab.workers.Seller;
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
    public void testPaySalaryIfWorked() {
        cleaner.setWorkingHours(8);
        seller.setWorkingHours(9);
        manager.setWorkingHours(7);

        countingHouse.paySalary(cleaner, seller, manager);

        Assert.assertEquals(100, countingHouse.getMoney(), 0);
    }

    @Test
    public void testPaySalaryIfNotWorked() {
        cleaner.setWorkingHours(2);
        seller.setWorkingHours(1);
        manager.setWorkingHours(5);

        countingHouse.paySalary(cleaner, seller, manager);

        Assert.assertEquals(1500, countingHouse.getMoney(), 0);
    }

}
