package com.Lab.products;

public enum Fish implements Purchaseable {
    SALMON("Лосось", 150.0),
    CARP("Карась", 90.0),
    PERCH("Окунь", 95.9),
    PIKE("Щука", 97.3),
    ANCHOVY("Анчоус", 84.7);

    private final String title;
    private final double price;

    Fish(String title, double price) {
        this.title = title;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Название продукта: " + title +
                ", цена: " + price;
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
