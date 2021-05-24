package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private double price;
    private String cheque;
    private Timestamp time;

    public Purchase(int userId, double price, String cheque) {
        this.userId = userId;
        this.price = price;
        this.cheque = cheque;
        time = new Timestamp(System.currentTimeMillis());
    }

}
