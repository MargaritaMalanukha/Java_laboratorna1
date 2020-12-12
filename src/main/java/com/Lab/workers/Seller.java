package com.Lab.workers;

import com.Lab.purchase.Purchase;

public class Seller extends Worker {

    private double money;
    {
        salary = 500;
    }

    public Seller() {
        name = "";
        surname = "";
    }

    public Seller(String name, String surname, double bonus) {
        this.name = name;
        this.surname = surname;
        this.bonus = bonus;
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

        money += purchase.getFullPrice();
        System.out.println("Successful payment!");

    }



}
