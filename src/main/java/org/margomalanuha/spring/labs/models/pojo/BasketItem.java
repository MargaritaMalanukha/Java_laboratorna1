package org.margomalanuha.spring.labs.models.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "basket_items")
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public BasketItem(Product product, User user) {
        this.product = product;
        this.user = user;
    }

}
