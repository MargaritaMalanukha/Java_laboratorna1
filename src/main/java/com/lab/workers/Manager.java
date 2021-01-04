package com.lab.workers;

import com.lab.customer.Customer;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Manager extends Worker {

    private List<String> reviews = new LinkedList<>();

    public Manager() {
        name = "";
        surname = "";
        salary = 600;
    }

    public Manager(String name, String surname, double bonus) {
        this.name = name;
        this.surname = surname;
        this.bonus = bonus;
        salary = 600;
    }

    public Manager(double bonus) {
        this.bonus = bonus;
        salary = 600;
    }

    public void getOpinion(Customer customer, String opinion) {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer " + customer.getName() + " " + customer.getSurname());
        builder.append(" with ID " + customer.getCustomerId() + ": " + opinion + "\n");
        reviews.add(new String(builder));
    }

    public void printAllCustomersWithOpinion(){
        Logger logger = Logger.getLogger(Manager.class.getName());
        for (String review : reviews) {
            String customer = review.split(":")[0];
            logger.log(Level.FINEST, customer);
        }
    }

    public List<String> getReviews() {
        return reviews;
    }
}
