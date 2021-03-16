package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketItem {

    private int id;
    private int productId;
    private int userId;

    public BasketItem(int productId, int userId) {
        this.productId = productId;
        this.userId = userId;
    }

}
