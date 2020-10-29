package com.Lab.customer;

import com.Lab.purchase.Purchase;

import java.util.LinkedList;
import java.util.Objects;

public class Customer {

    private final int customerId;

    private String name;
    private String surname;

    private Card card;

    public final LinkedList<Purchase> bucket;

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;
        customerId = hashCode();
        card = new Card();
        bucket = new LinkedList<>();
        System.out.println("New customer is added. (" + name + ", " + surname + ", " + customerId + ")");
    }

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

    public class Card {

        private int cardId = hashCode();
        private double bonus;
        private final float percent = 1;

        public void generateBonus(double purchasePayment) {
            bonus += purchasePayment * 0.01 * percent;
            System.out.println("Bonus of (" + name + "," + surname + ") is " + bonus);
        }

        public double getBonus() {
            return bonus;
        }

        public int getCardId() {
            return cardId;
        }
    }

}
