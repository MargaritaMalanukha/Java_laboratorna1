package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.models.pojo.UserType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    void register(User user);
    User login(String email, String password);
    void updateData(User user);
    void deactivateUser(int userId);
    List<User> getAllUsers();
    List<UserType> getAllUserTypes();
    List<User> getUsersByTitle(String title);
    boolean isMainAdmin(User user);
    void upgradeToAdmin(Integer userId);
    void downgradeToUser(Integer userId);
    boolean isAdmin(User user);


}
