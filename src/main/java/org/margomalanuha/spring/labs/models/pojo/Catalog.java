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
@Table(name = "catalogs")
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @JoinColumn(name = "catalog_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private Catalog catalog;

    @OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "catalog", fetch = FetchType.LAZY)
    private List<Catalog> catalogs = new ArrayList<>();

    public Catalog(String title, Catalog catalog) {
        this.title = title;
        setCatalog(catalog);
    }

    public Catalog(int id, String title, Catalog catalog) {
        this.id = id;
        this.title = title;
        setCatalog(catalog);
    }

    public Catalog(Catalog catalog) {
        setCatalog(catalog);
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }
}
