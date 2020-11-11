package com.Lab.products;

public enum Meat implements Purchaseable {
    CHICKEN("Курица", 90.0),
    BEEF("Говядина", 210.0),
    PORK("Свинина", 305.5),
    TURKEY_MEAT("Индюшатина", 95.0),
    HORSEMEAT("Конина", 340.0);

    private final String title;
    private final double price;

    Meat(String title, double price) {
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
