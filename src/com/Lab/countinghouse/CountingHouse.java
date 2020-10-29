package com.Lab.countinghouse;

import com.Lab.workers.Cleaner;
import com.Lab.workers.Manager;
import com.Lab.workers.Seller;

public class CountingHouse {

    private double money;

    private final Cleaner cleaner;
    private final Seller seller;
    private final Manager manager;
    {
            cleaner = new Cleaner("Ivan", "Ivanov");
            cleaner.setWorkingHours(8);
            seller = new Seller("Petr", "Petrov");
            seller.setWorkingHours(9);
            manager = new Manager("Bohdan", "Sidorov");
            manager.setWorkingHours(7);
    }

    public void increaseMoney(double moneyToAdd) {
        money += moneyToAdd;
    }

    public void paySalary(Cleaner cleaner, Seller seller, Manager manager) {
        if (cleaner.equals(this.cleaner)){
            money -= this.cleaner.getSalary();
            System.out.println("Cleaner " + cleaner.getName() + " " + cleaner.getSurname() + " got his salary.");
        }
        if (seller.equals(this.seller)){
            money -= this.seller.getSalary();
            System.out.println("Seller " + seller.getName() + " " + seller.getSurname() + " got his salary.");
        }
        if (manager.equals(this.manager)){
            money -= this.manager.getSalary();
            System.out.println("Manager " + manager.getName() + " " + manager.getSurname() + " got his salary.");
        }
    }

    public double getMoney() {
        return money;
    }
}
