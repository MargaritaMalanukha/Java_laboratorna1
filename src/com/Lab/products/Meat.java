package com.Lab.products;

public enum Meat implements Purchaseable {
    CHICKEN("Курица", 90),
    BEEF("Говядина", 210),
    PORK("Свинина", 305.5),
    TURKEY_MEAT("Индюшатина", 95),
    HORSEMEAT("Конина", 340);

    private String title;
    private double price;

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
