package com.Lab.products;

public enum Meat implements Purchaseable {
    CHICKEN("Chicken", 90.0),
    BEEF("Beef", 210.0),
    PORK("Pork", 305.5),
    TURKEY_MEAT("Turkey Meat", 95.0),
    HORSEMEAT("Horse Meat", 340.0);

    private final String title;
    private final double price;

    Meat(String title, double price) {
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

    public double getPrice() { return price; }

}
