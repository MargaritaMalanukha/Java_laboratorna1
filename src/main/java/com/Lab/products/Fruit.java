package com.Lab.products;

public enum Fruit implements Purchaseable {
    APPLE("Apple", 23.2),
    PEAR("Pear", 20.7),
    ORANGE("Orange", 42.5),
    GRAPE("Grape", 20.0),
    MELON("Melon", 11.45),
    PINEAPPLE("Pineapple", 50.62);

    private final String title;
    private final double price;

    Fruit(String title, double price) {
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
