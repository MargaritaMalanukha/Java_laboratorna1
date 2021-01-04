package com.lab.products;

public enum Fish implements Purchaseable {
    SALMON("Salmon", 150.0),
    CARP("Carp", 90.0),
    PERCH("Perch", 95.9),
    PIKE("Pike", 97.3),
    ANCHOVY("Anchovy", 84.7);

    private final String title;
    private final double price;

    Fish(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "The title of product is: " + title +
                ", price: " + price;
    }

    @Override
    public String getTitle(){
        return title;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
