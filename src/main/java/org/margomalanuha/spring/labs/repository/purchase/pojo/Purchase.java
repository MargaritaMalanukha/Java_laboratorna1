package org.margomalanuha.spring.labs.repository.purchase.pojo;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    private int id;
    private int userId;
    private double price;
    private Timestamp time;

}
