package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int productId;
    private int userId;

    public BasketItem(int productId, int userId) {
        this.productId = productId;
        this.userId = userId;
    }

}
