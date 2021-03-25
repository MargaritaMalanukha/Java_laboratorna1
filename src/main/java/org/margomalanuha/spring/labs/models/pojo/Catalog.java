package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Catalog {

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
