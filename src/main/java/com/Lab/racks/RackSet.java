package com.Lab.racks;

import java.util.ArrayList;

public class RackSet {

    private final ArrayList<Rack> racks; //композиция - has a (строгое).
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

    public ArrayList<Rack> getRacks() {
        return racks;
    }





}
