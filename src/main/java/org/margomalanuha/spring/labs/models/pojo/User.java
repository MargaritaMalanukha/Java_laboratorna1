package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "mobile_phone")
    private String mobile_phone;

    @Column(name = "address")
    private String address;

    @JoinColumn(name = "user_type_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private UserType userType;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BasketItem> basketItems;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Purchase> purchases;

    public User(String name, String surname, String email, String password, UserType userType) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.userType = userType;
        isActive = true;
    }

}
