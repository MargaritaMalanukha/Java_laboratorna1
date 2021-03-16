package org.margomalanuha.spring.labs.models.pojo;

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

    public Purchase(int userId, double price) {
        this.userId = userId;
        this.price = price;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    }

}
