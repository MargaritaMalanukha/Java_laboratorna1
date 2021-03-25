package org.margomalanuha.spring.labs.service;

import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String name, String surname, String email, String password) {
        int id = userRepository.create(new User(name, surname, email, password));
        return userRepository.getById(id);
    }

    @Override
    public void updateData(User user) {
        userRepository.update(user);
    }

    @Override
    public void deactivateUser(User user) {
        user.setActive(false);
        userRepository.update(user);
    }
}
