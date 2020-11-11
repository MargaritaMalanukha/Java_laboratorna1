package com.Lab.products;

public enum Sweet implements Purchaseable {
    CANDIES("Конфеты", 60.3),
    COOKIE("Печенье", 70.2),
    CAKE("Торт", 120.99),
    DONUT("Пончик", 35.0),
    ICE_CREAM("Мороженое", 20.0),
    CHOCOLATE("Шоколад", 25.0);

    private final String title;
    private final double price;

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
