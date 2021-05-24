package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
