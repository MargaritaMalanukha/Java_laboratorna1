package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "catalogs")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @JoinColumn(name = "catalog_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Catalog catalog;

    @OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
    private List<Catalog> catalogs = new ArrayList<>();

    public Catalog(String title, Catalog catalog) {
        this.title = title;
        this.catalog = catalog;
    }
}
