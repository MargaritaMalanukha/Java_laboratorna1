package com.Lab.products;

public enum Vegetable implements Purchaseable {
    TOMATO("Tomato", 17.5),
    POTATO("Potato", 10.2),
    CARROT("Carrot", 12.5),
    PEPPER("Pepper", 20.0),
    ONION("Onion", 5.0),
    GARLIC("Garlic", 10.0),
    CUCUMBER("Cucumber", 14.5);

    private final String title;
    private final double price;

    Vegetable(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "The title of product is: " + title +
                ", price: " + price;
    }

    public String getTitle(){
        return title;
    }

    public double getPrice() {
        return price;
    }

}
