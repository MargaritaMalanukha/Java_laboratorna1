package org.margomalanuha.spring.labs.repository.account.pojo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRight {

    private int id;
    private int rightId;
    private int userTypeId;

}
