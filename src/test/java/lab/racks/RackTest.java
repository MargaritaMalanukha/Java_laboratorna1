package lab.racks;

import com.lab.racks.Rack;
import org.junit.Assert;
import org.junit.Test;

public class RackTest {

    private Rack rack;

    @Test
    public void GenerateRack_WithValidProductTitle_ShouldReturnInitializeAndFillNewRack() {
        rack = new Rack("Fish");
        Assert.assertEquals(5, rack.getProducts().size());
    }

    @Test
    public void GenerateRack_WithInvalidProductTitle_ShouldReturnEmptyRack() {
        rack = new Rack("wrongTitle");
        Assert.assertEquals(0, rack.getProducts().size());
    }

}
