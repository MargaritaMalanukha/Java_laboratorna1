package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.User;

public interface UserService {

    User register(String name, String surname, String email, String password);
    void updateData(User user);
    void deactivateUser(User user);

}
