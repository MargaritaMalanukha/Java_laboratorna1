package lab.racks;

import com.lab.racks.RackSet;
import org.junit.Assert;
import org.junit.Test;

public class RackSetTest {

    private final RackSet rackSet = new RackSet();

    @Test
    public void GenerateRacks_WithEmptyRacks_ShouldReturnGeneratedRacks() {
        rackSet.generateRacks();
        Assert.assertEquals(6, rackSet.getRacks().size());
    }

}
