package com.LabJUnitTest.racks;

import com.Lab.racks.Rack;
import org.junit.Assert;
import org.junit.Test;

public class RackTest {

    private Rack rack;

    @Test
    public void testGenerateRack() {
        rack = new Rack("Fish");

        Assert.assertEquals(5, rack.getProducts().size());
    }

    @Test
    public void testGenerateWrongRack() {
        rack = new Rack("wrongTitle");

        Assert.assertEquals(0, rack.getProducts().size());
    }

}
