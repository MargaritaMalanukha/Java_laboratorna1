package com.lab.workers;

import com.lab.purchase.Purchase;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Seller extends Worker {

    private double money;

    public Seller() {
        name = "";
        surname = "";
        salary = 500;
    }

    public Seller(String name, String surname, double bonus) {
        this.name = name;
        this.surname = surname;
        this.bonus = bonus;
        salary = 500;
    }

    public Seller(double bonus) {
        this.bonus = bonus;
    }

    public double getMoney() {
        return money;
    }

    public void getCheque(Purchase purchase) {
        purchase.printCheque();
    }

    public void pay(Purchase purchase) {
        Logger logger = Logger.getLogger(Seller.class.getName());

        money += purchase.getFullPrice();
        logger.log(Level.FINEST,"Successful payment!");

    }
}
