package com.Lab.products;

public enum Sweet implements Purchaseable {
    CANDIES("Конфеты", 60.3),
    COOKIE("Печенье", 70.2),
    CAKE("Торт", 120.99),
    DONUT("Пончик", 35),
    ICE_CREAM("Мороженое", 20),
    CHOCOLATE("Шоколад", 25);

    private String title;
    private double price;

    Sweet(String title, double price) {
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

    public double getPrice() { return price; }
}
