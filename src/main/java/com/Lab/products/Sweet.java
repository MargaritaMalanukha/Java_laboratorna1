package com.Lab.products;

public enum Sweet implements Purchaseable {
    CANDIES("Candies", 60.3),
    COOKIE("Cookie", 70.2),
    CAKE("Cake", 120.99),
    DONUT("Donut", 35.0),
    ICE_CREAM("IceCream", 20.0),
    CHOCOLATE("Chocolate", 25.0);

    private final String title;
    private final double price;

    Sweet(String title, double price) {
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
