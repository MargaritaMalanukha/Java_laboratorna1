package org.margomalanuha.spring.labs.repository.products.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private int id;
    private String title;
    private double price;
    private int catalogId;

}
