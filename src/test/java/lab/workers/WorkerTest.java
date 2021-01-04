package lab.workers;

import com.lab.workers.Cleaner;
import com.lab.workers.Manager;
import com.lab.workers.Worker;
import org.junit.Assert;
import org.junit.Test;

public class WorkerTest {

    Worker cleaner = new Cleaner();
    {
        cleaner.setWorkingHours(8);
    }

    @Test
    public void Equals_WithWorkerEqualToIdealWorkerOfHisType_ShouldReturnTrue() {
        Cleaner cleanerTest = new Cleaner();
        cleanerTest.setWorkingHours(8);
        Assert.assertEquals(cleaner, cleanerTest);
    }

    @Test
    public void Equals_WithWorkerNonEqualToIdealWorkerOfHisType_ShouldReturnFalse() {
        Cleaner cleanerTest = new Cleaner();
        cleanerTest.setWorkingHours(6);
        Assert.assertNotEquals(cleaner, cleanerTest);
    }

    @Test
    public void Equals_WithWorkerTypeNonEqualToIdealWorkerType_ShouldReturnFalse() {
        Manager manager = new Manager();
        manager.setWorkingHours(8);
        Assert.assertNotEquals(cleaner, manager);
    }

}
