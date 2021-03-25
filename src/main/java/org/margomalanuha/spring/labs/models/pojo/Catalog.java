package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Catalog {

    private int id;
    private String title;
    private Integer upperCatalogId;

    public Catalog(String title) {
        this.title = title;
    }

}
