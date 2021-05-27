package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private double price;

    @JoinColumn(name = "catalog_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Catalog catalog;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<BasketItem> basketItems = new ArrayList<>();

    public Product(String title, double price, Catalog catalog) {
        this.title = title;
        this.price = price;
        this.catalog = catalog;
    }

}
