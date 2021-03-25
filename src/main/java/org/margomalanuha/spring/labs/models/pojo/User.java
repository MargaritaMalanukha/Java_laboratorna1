package org.margomalanuha.spring.labs.models.pojo;

import lombok.*;
import org.margomalanuha.spring.labs.repository.UserTypeRepository;

import java.util.NoSuchElementException;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int userTypeId;
    private boolean isActive;

    public User(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        userTypeId = new UserTypeRepository().getAll().stream()
                .filter(e -> e.getTitle().equals("User"))
                .mapToInt(UserType::getId)
                .findFirst().orElseThrow(NoSuchElementException::new);
        isActive = true;
    }

}
