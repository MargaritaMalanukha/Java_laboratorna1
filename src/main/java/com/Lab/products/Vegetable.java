package com.Lab.products;

public enum Vegetable implements Purchaseable {
    TOMATO("Помидор", 17.5),
    POTATO("Картофель", 10.2),
    CARROT("Морковь", 12.5),
    PEPPER("Перец", 20.0),
    ONION("Лук", 5.0),
    GARLIC("Чеснок", 10.0),
    CUCUMBER("Огурец", 14.5);

    private final String title;
    private final double price;

    Vegetable(String title, double price) {
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
