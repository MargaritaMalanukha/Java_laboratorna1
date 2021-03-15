package org.margomalanuha.spring.labs.repository.purchase.pojo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasketItem {

    private int id;
    private int productId;
    private int userId;

}
