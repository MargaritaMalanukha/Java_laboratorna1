package com.Lab.products;

public enum Vegetable implements Purchaseable {
    TOMATO("Помидор", 17.5),
    POTATO("Картофель", 10.2),
    CARROT("Морковь", 12.5),
    PEPPER("Перец", 20),
    ONION("Лук", 5),
    GARLIC("Чеснок", 10),
    CUCUMBER("Огурец", 14.5);

    private String title;
    private double price;

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
