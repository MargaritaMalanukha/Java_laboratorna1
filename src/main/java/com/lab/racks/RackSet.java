package com.lab.racks;

import java.util.ArrayList;
import java.util.List;

public class RackSet {

    private final List<Rack> racks; //композиция - has a (строгое).
    private final String [] racksTitle = { "Fish", "Fruit", "Meat", "MilkProduct", "Sweet", "Vegetable" };

    public RackSet() {
        racks = new ArrayList<>();
    }

    public void generateRacks() {

        for (String title : racksTitle) {
            racks.add(new Rack(title));
        }

    }

    public void printRacks() {

        for (Rack rack : racks) {
            rack.printRack();
        }

    }

    public List<Rack> getRacks() {
        return racks;
    }





}
