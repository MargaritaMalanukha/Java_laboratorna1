package com.lab.workers;

import com.lab.racks.RackSet;

public class Cleaner extends Worker {

    private RackSet rackSet;

    public Cleaner() {
        name = "";
        surname = "";
        salary = 300;
    }

    public Cleaner(String name, String surname, double bonus) {
        this.name = name;
        this.surname = surname;
        this.bonus = bonus;
        salary = 300;
    }

    public Cleaner(double bonus) {
        this.bonus = bonus;
        salary = 300;
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
