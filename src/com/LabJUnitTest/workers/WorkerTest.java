package com.LabJUnitTest.workers;

import com.Lab.workers.Cleaner;
import com.Lab.workers.Manager;
import com.Lab.workers.Worker;
import org.junit.Assert;
import org.junit.Test;

public class WorkerTest {

    Worker cleaner = new Cleaner();
    {
        cleaner.setWorkingHours(8);
    }

    @Test
    public void testEqualsIfEqual() {
        Cleaner cleanerTest = new Cleaner();
        cleanerTest.setWorkingHours(8);
        Boolean result = cleaner.equals(cleanerTest);

        Assert.assertEquals(true, result);
    }

    @Test
    public void testEqualsIfNotEqual() {
        Cleaner cleanerTest = new Cleaner();
        cleanerTest.setWorkingHours(6);
        Boolean result = cleaner.equals(cleanerTest);

        Assert.assertEquals(false, result);
    }

    @Test
    public void testEqualsIfDifferentTypes() {
        Manager manager = new Manager();
        manager.setWorkingHours(8);
        Boolean result = cleaner.equals(manager);

        Assert.assertEquals(false, result);
    }

}
