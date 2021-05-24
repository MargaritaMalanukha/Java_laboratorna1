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
public class Catalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private Integer upperCatalogId;

    public Catalog(String title, Integer upperCatalogId) {
        this.title = title;
        this.upperCatalogId = upperCatalogId;
    }

    public Integer getUpperCatalogId() {
        return upperCatalogId == 0 ? null : upperCatalogId;
    }
}
