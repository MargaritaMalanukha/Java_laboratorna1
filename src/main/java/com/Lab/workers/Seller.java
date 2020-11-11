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

    public Seller(String name, String surname) {
        this.name = name;
        this.surname = surname;
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
