package com.Lab.products;

public interface Purchaseable {
    String getTitle();
    double getPrice();
    default int compare(Purchaseable product) {
        return Double.compare(this.getPrice(), product.getPrice());
    }
}
