package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.service.exceptions.WrongEmailOrPasswordException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    User register(User user);
    User login(String email, String password) throws WrongEmailOrPasswordException;
    void updateData(User user);
    void deactivateUser(User user);
    List<User> getAllUsers();

}
