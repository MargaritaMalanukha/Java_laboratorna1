package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.User;

public interface UserService {

    void register(String name, String surname, String email, String password);
    void updateData(User user);
    void deactivateUser(User user);

}
