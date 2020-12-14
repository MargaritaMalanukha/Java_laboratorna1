package Lab.workers;

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
    public void Equals_WithWorkerEqualToIdealWorkerOfHisType_ShouldReturnTrue() {
        Cleaner cleanerTest = new Cleaner();
        cleanerTest.setWorkingHours(8);
        Assert.assertTrue(cleaner.equals(cleanerTest));
    }

    @Test
    public void Equals_WithWorkerNonEqualToIdealWorkerOfHisType_ShouldReturnFalse() {
        Cleaner cleanerTest = new Cleaner();
        cleanerTest.setWorkingHours(6);
        Assert.assertFalse(cleaner.equals(cleanerTest));
    }

    @Test
    public void Equals_WithWorkerTypeNonEqualToIdealWorkerType_ShouldReturnFalse() {
        Manager manager = new Manager();
        manager.setWorkingHours(8);
        Assert.assertFalse(cleaner.equals(manager));
    }

}
