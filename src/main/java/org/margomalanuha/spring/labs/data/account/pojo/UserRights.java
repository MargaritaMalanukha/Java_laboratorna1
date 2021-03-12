package org.margomalanuha.spring.labs.data.account.pojo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
public class UserRights {

    private int id;
    private int rightsId;
    private int userTypeId;

}
