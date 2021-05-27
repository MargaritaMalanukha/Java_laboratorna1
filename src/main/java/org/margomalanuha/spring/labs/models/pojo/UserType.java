package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_types")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(mappedBy = "userType", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

}
