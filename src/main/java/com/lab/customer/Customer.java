package com.lab.customer;

import com.lab.purchase.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Customer {

    private final int customerId;

    private final String name;
    private final String surname;

    private final Card card;

    private List<Purchase> bucket = new LinkedList<>();

    public Customer(String name, String surname) {
        Logger logger = LoggerFactory.getLogger(Customer.class);
        this.name = name;
        this.surname = surname;
        customerId = hashCode();
        card = new Card();
        logger.debug("New customer is added. ({}, {}, {})", name, surname, customerId);
    }

    public void setBucket(List<Purchase> bucket) { this.bucket = bucket; }

    public String getName() {
        return name;
    }

    public String getSurname() { return surname; }

    public int getCustomerId() {
        return customerId;
    }

    public Card getCard() { return card; }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Customer c = (Customer) obj;
        return customerId == c.customerId && (Objects.equals(name, c.name)) && (Objects.equals(surname, c.surname));
    }

    @Override
    public int hashCode() {
        final int prime = 17;
        int result = 1;
        result += prime * result + ((name == null) ? 0 : name.hashCode());
        result += prime * result + ((surname == null) ? 0 : surname.hashCode());
        return result;
    }

    public List<Purchase> getBucket() {
        return bucket;
    }

    public class Card {

        private double bonus;
        private static final float PERCENT = 1;

        public void generateBonus(double purchasePayment) {
            Logger logger = LoggerFactory.getLogger(Card.class);
            bonus += purchasePayment * 0.01 * PERCENT;
            logger.debug("Bonus of ({},{}) is {}.", name, surname, bonus);
        }

        public double getBonus() {
            return bonus;
        }
    }

}
