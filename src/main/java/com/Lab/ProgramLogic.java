package com.Lab;

import com.Lab.countinghouse.CountingHouse;
import com.Lab.customer.Customer;
import com.Lab.products.Purchaseable;
import com.Lab.purchase.Purchase;
import com.Lab.racks.Rack;
import com.Lab.workers.Cleaner;
import com.Lab.workers.Manager;
import com.Lab.workers.Seller;

import java.util.ArrayList;
import java.util.Random;

public class ProgramLogic {

    private final CountingHouse countingHouse = new CountingHouse();

    private final Manager manager = new Manager("Alex", "Sokolov", 100);
    private final Seller seller = new Seller("John", "Smit", 50);
    private final Cleaner cleaner = new Cleaner("Jenny", "Scott", 70);

    private final ArrayList<Customer> customers = new ArrayList<Customer>();

    public ProgramLogic() { //примерный ход развития событий в программе.

        cleaner.prepareRacks();

        customers.add(new Customer("Alexander", "Stepanenko"));
        customers.add(new Customer("Stepan", "Petrenko"));
        customers.add(new Customer("Sergey", "Rudenko"));
        customers.add(new Customer("Georgiy", "Ivanov"));

        for (Customer customer : customers) {
            customer.bucket.add(getRandomPurchase());
            customer.getCard().generateBonus(customer.bucket.getLast().getFullPrice());
            seller.pay(customer.bucket.getLast());
            seller.getCheque(customer.bucket.getLast());
        }

        manager.getOpinion(customers.get(1), "Nice shop");
        manager.getOpinion(customers.get(3), "I saw an expired milk in this market.");

        System.out.println(manager.getReviews());
        manager.printAllCustomersWithOpinion();

        manager.setWorkingHours(7);
        seller.setWorkingHours(9);
        cleaner.setWorkingHours(8);

        countingHouse.increaseMoney(seller.getMoney());
        countingHouse.paySalary(cleaner, seller, manager);

        System.out.println("Money for the whole day: " + countingHouse.getMoney());
    }

    private Purchase getRandomPurchase() {
        Purchase purchase = new Purchase();
        ArrayList<Rack> racks = cleaner.getRackSet().getRacks();
        for (Rack rack : racks) {
            int item = new Random().nextInt(rack.getProducts().size());
            int i = 0;
            for (Purchaseable product : rack.getProducts()) {
                if (i == item) {
                    purchase.add(product);
                    break;
                }
                i++;
            }
        }
        return purchase;
    }

}
