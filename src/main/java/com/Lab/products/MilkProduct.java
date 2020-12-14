package com.Lab.products;

public enum MilkProduct implements Purchaseable {
    MILK("Milk", 25),
    KEFIR("Kefir", 23.3),
    YOGURT("Yogurt", 30.25),
    CHEESE("Cheese", 50.0),
    BUTTER("Butter", 40.5);

    private final String title;
    private final double price;

    MilkProduct(String title, double price) {
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
