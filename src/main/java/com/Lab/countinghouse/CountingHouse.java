package com.lab.countinghouse;

import com.lab.workers.Cleaner;
import com.lab.workers.Manager;
import com.lab.workers.Seller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountingHouse {

    private double money;

    private final Cleaner idealCleaner;
    private final Seller idealSeller;
    private final Manager idealManager;

    public CountingHouse() {
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
        Logger logger = LoggerFactory.getLogger(CountingHouse.class);
        String str = " got his salary.";
        if (cleaner.getWorkingHours() == idealCleaner.getWorkingHours() && cleaner.getSalary() == idealCleaner.getSalary()){
            money -= cleaner.getSalary();
            logger.debug("Cleaner {} {} {}", cleaner.getName(), cleaner.getSurname(), str);
        }
        if (seller.getWorkingHours() == idealSeller.getWorkingHours() && seller.getSalary() == idealSeller.getSalary()){
            money -= seller.getSalary();
            logger.debug("Seller {} {} {}", seller.getName(), seller.getSurname(), str);
        }
        if (manager.getWorkingHours() == idealManager.getWorkingHours() && manager.getSalary() == idealManager.getSalary()){
            money -= manager.getSalary();
            logger.debug("Manager {} {} {}", manager.getName(), manager.getSurname(), str);
        }
    }

    public double getMoney() {
        return money;
    }
}
