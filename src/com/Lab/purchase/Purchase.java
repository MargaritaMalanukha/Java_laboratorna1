package com.Lab.purchase;

import com.Lab.products.Purchaseable;

import java.util.ArrayList;

final public class Purchase {

    private long purchaseId;
    private ArrayList<Purchaseable> products = new ArrayList<>();

    public Purchase() {
        purchaseId = hashCode();
    }

    public Purchase(long purchaseId)
    {
        this.purchaseId = purchaseId;
    }

    public Purchase(String products) {

    }

    public void add(Purchaseable product) { products.add(product); }

    public void delete(Purchaseable product)
    {
        products.remove(product);
    }

    public long getPurchaseId() {
        return purchaseId;
    }

    public ArrayList<Purchaseable> getProducts() {
        return products;
    }

    public double getFullPrice() {
        double sum = 0;
        for (Purchaseable product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    public void printCheque() {
        System.out.println("----------CHEQUE----------");
        System.out.println("Thank you for visiting us! ");
        System.out.println("Your products: ");
        for (Purchaseable product : products) {
            System.out.println(product.toString());
        }
        System.out.println("Your sum: " + getFullPrice());
        System.out.println("See you next time!");
        System.out.println("PurchaseId: " + purchaseId);
        System.out.println("----------CHEQUE----------");
    }
}
