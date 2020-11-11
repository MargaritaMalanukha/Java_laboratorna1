package com.Lab.workers;

import com.Lab.customer.Customer;

import java.util.LinkedList;

public class Manager extends Worker {

    private LinkedList<String> reviews = new LinkedList<>();
    {
        salary = 600;
    }

    public Manager() {
        name = "";
        surname = "";
    }

    public Manager(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public void getOpinion(Customer customer, String opinion) {
        StringBuilder builder = new StringBuilder();
        builder.append("Customer " + customer.getName() + " " + customer.getSurname());
        builder.append(" with ID " + customer.getCustomerId() + ": " + opinion + "\n");
        reviews.add(new String(builder));
    }

    public void printAllCustomersWithOpinion(){
        for (String review : reviews) {
            System.out.println(review.split(":")[0]);
        }
    }

    public LinkedList<String> getReviews() {
        return reviews;
    }
}
