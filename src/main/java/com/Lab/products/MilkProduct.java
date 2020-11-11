package com.Lab.products;

public enum MilkProduct implements Purchaseable {
    MILK("Молоко", 25),
    KEFIR("Кефир", 23.3),
    YOGURT("Йогурт", 30.25),
    CHEESE("Сыр", 50.0),
    BUTTER("Масло", 40.5);

    private final String title;
    private final double price;

    MilkProduct(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Название продукта: " + title +
                ", цена: " + price;
    }

    public String getTitle(){
        return title;
    }

    public double getPrice() {
        return price;
    }
}
