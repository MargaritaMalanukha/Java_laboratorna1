package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Catalog {

    private int id;
    private String title;

    public Catalog(String title) {
        this.title = title;
    }

}
