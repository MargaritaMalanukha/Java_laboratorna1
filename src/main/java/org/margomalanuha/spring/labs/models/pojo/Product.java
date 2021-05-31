package org.margomalanuha.spring.labs.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    private Catalog catalog;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<BasketItem> basketItems = new ArrayList<>();

    public Product(String title, double price, Catalog catalog) {
        this.title = title;
        this.price = price;
        this.catalog = catalog;
    }

    public String toString() {
        return "id=" + id + " title=" + title + " price=" + price;
    }

}
