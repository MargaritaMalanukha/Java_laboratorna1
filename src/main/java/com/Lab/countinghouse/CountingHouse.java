package com.Lab.countinghouse;

import com.Lab.workers.Cleaner;
import com.Lab.workers.Manager;
import com.Lab.workers.Seller;

public class CountingHouse {

    private double money;

    private final Cleaner idealCleaner;
    private final Seller idealSeller;
    private final Manager idealManager;
    {
            idealCleaner = new Cleaner("Ivan", "Ivanov", 0);
            idealCleaner.setWorkingHours(8);
            idealSeller = new Seller("Petr", "Petrov", 0);
            idealSeller.setWorkingHours(9);
            idealManager = new Manager("Bohdan", "Sidorov", 0);
            idealManager.setWorkingHours(7);
    }

    public void increaseMoney(double moneyToAdd) {
        money += moneyToAdd;
    }

    public void paySalary(Cleaner cleaner, Seller seller, Manager manager) {
        if (cleaner.equals(idealCleaner)){
            money -= cleaner.getSalary();
            System.out.println("Cleaner " + cleaner.getName() + " " + cleaner.getSurname() + " got his salary.");
        }
        if (seller.equals(idealSeller)){
            money -= seller.getSalary();
            System.out.println("Seller " + seller.getName() + " " + seller.getSurname() + " got his salary.");
        }
        if (manager.equals(idealManager)){
            money -= manager.getSalary();
            System.out.println("Manager " + manager.getName() + " " + manager.getSurname() + " got his salary.");
        }
    }

    public double getMoney() {
        return money;
    }
}
