package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private int id;
    private String title;
    private double price;
    private int catalogId;

    public Product(String title, double price, int catalogId) {
        this.title = title;
        this.price = price;
        this.catalogId = catalogId;
    }

}
