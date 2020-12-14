package Lab.workers;

import com.Lab.workers.Cleaner;
import org.junit.Assert;
import org.junit.Test;

public class CleanerTest {

    private final Cleaner cleaner = new Cleaner();
    {
        cleaner.setWorkingHours(8);
    }

    @Test
    public void PrepareRacks_WithEmptyRacks_ShouldReturnFilledRacks() {
        cleaner.prepareRacks();
        Assert.assertEquals(6, cleaner.getRackSet().getRacks().size());
    }

}
