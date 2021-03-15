package org.margomalanuha.spring.labs.repository.account.pojo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int userTypeId;
    private boolean isActive;

}
