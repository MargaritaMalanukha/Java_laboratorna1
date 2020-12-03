package com.Lab.workers;

import com.Lab.racks.RackSet;

public class Cleaner extends Worker {

    private RackSet rackSet;
    {
        salary = 300;
    }

    public Cleaner() {
        name = "";
        surname = "";
    }

    public Cleaner(String name, String surname, double bonus) {
        this.name = name;
        this.surname = surname;
        this.bonus = bonus;
    }

    public void prepareRacks() {
        rackSet = new RackSet();
        rackSet.generateRacks();
        rackSet.printRacks();
    }

    public RackSet getRackSet() {
        return rackSet;
    }
}
