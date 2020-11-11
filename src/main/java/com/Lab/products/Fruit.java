package com.Lab.products;

public enum Fruit implements Purchaseable {
    APPLE("Яблоко", 23.2),
    PEAR("Груша", 20.7),
    ORANGE("Апельсин", 42.5),
    GRAPE("Виноград", 20.0),
    MELON("Дыня", 11.45),
    PINEAPPLE("Ананас", 50.62);

    private final String title;
    private final double price;

    Fruit(String title, double price) {
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
