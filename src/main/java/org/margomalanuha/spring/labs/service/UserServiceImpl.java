package org.margomalanuha.spring.labs.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.margomalanuha.spring.labs.models.pojo.User;
import org.margomalanuha.spring.labs.repository.UserRepository;
import org.margomalanuha.spring.labs.service.exceptions.WrongEmailOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Getter
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        userRepository.create(user);
        return user;
    }

    @Override
    public User login(String email, String password) throws WrongEmailOrPasswordException {
        User user = userRepository.getAll().stream()
                .filter(e -> e.getEmail().equals(email) && e.getPassword().equals(password))
                .findFirst().orElse(null);
        if (user == null) throw new WrongEmailOrPasswordException();
        return user;
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

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAll();
    }
}
