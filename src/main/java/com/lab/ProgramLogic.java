package com.lab;

import com.lab.countinghouse.CountingHouse;
import com.lab.customer.Customer;
import com.lab.products.Purchaseable;
import com.lab.purchase.Purchase;
import com.lab.racks.Rack;
import com.lab.workers.Cleaner;
import com.lab.workers.Manager;
import com.lab.workers.Seller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.security.SecureRandom;
import java.util.List;

public class ProgramLogic {

    private final CountingHouse countingHouse = new CountingHouse();

    private final Manager manager = new Manager("Alex", "Sokolov", 100);
    private final Seller seller = new Seller("John", "Smit", 50);
    private final Cleaner cleaner = new Cleaner("Jenny", "Scott", 70);

    private final ArrayList<Customer> customers = new ArrayList<>();

    public ProgramLogic() { //примерный ход развития событий в программе.
        Logger logger = LoggerFactory.getLogger(ProgramLogic.class);

        cleaner.prepareRacks();

        customers.add(new Customer("Alexander", "Stepanenko"));
        customers.add(new Customer("Stepan", "Petrenko"));
        customers.add(new Customer("Sergey", "Rudenko"));
        customers.add(new Customer("Georgiy", "Ivanov"));

        for (Customer customer : customers) {
            customer.getBucket().add(getRandomPurchase());
            customer.getCard().generateBonus(customer.getBucket().get(customer.getBucket().size() - 1).getFullPrice());
            seller.pay(customer.getBucket().get(customer.getBucket().size() - 1));
            seller.getCheque(customer.getBucket().get(customer.getBucket().size() - 1));
        }

        manager.getOpinion(customers.get(1), "Nice shop");
        manager.getOpinion(customers.get(3), "I saw an expired milk in this market.");

        logger.debug("{}", manager.getReviews());
        manager.printAllCustomersWithOpinion();

        manager.setWorkingHours(7);
        seller.setWorkingHours(9);
        cleaner.setWorkingHours(8);

        countingHouse.increaseMoney(seller.getMoney());
        countingHouse.paySalary(cleaner, seller, manager);

        logger.debug("Money for the whole day: {}", countingHouse.getMoney());
    }

    private Purchase getRandomPurchase() {
        Purchase purchase = new Purchase();
        List<Rack> racks = cleaner.getRackSet().getRacks();
        SecureRandom random = new SecureRandom();
        for (Rack rack : racks) {
            int item = random.nextInt(rack.getProducts().size());
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
