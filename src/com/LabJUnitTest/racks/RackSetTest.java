package com.LabJUnitTest.racks;

import com.Lab.racks.RackSet;
import org.junit.Assert;
import org.junit.Test;

public class RackSetTest {

    private final RackSet rackSet = new RackSet();

    @Test
    public void testGenerateRacks() {
        rackSet.generateRacks();

        Assert.assertEquals(6, rackSet.getRacks().size());
    }

}
